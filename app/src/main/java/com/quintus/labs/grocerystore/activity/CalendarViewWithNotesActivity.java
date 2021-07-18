package com.quintus.labs.grocerystore.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.helper.CalendarDialog;
import com.quintus.labs.grocerystore.model.Event;
import com.quintus.labs.grocerystore.model.User;
import com.quintus.labs.grocerystore.retrofit.APIClient;
import com.quintus.labs.grocerystore.retrofit.APIInterface;
import com.quintus.labs.grocerystore.util.localstorage.LocalStorage;

import org.hugoandrade.calendarviewlib.CalendarView;

import java.text.DateFormatSymbols;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CalendarViewWithNotesActivity extends BaseActivity {

    private final static int CREATE_EVENT_REQUEST_CODE = 100;

    private String[] mShortMonths;
    private CalendarView mCalendarView;
    private CalendarDialog mCalendarDialog;

    private List<Event> mEventList = new ArrayList<>();

    private User user = null;
    private Activity activity = this;
    public static Intent makeIntent(Context context) {
        return new Intent(context, CalendarViewWithNotesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalStorage localStorage = new LocalStorage(getApplicationContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        user = gson.fromJson(userString, User.class);
        mShortMonths = new DateFormatSymbols().getShortMonths();

        initializeUI();
    }

    private void initializeUI() {

        setContentView(R.layout.activity_calendar_view_with_notes_sdk_21);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mCalendarView = findViewById(R.id.calendarView);
        mCalendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(int month, int year) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(mShortMonths[month]);
                    getSupportActionBar().setSubtitle(Integer.toString(year));
                }
            }
        });
        mCalendarView.setOnItemClickedListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClicked(List<CalendarView.CalendarObject> calendarObjects,
                                      Calendar previousDate,
                                      Calendar selectedDate) {
                if (calendarObjects.size() != 0) {
                    mCalendarDialog.setSelectedDate(selectedDate);
                    mCalendarDialog.show();
                }
                else {
                    long days = Duration.between(selectedDate.toInstant(), Calendar.getInstance().toInstant()).toDays();
                    if (diffYMD(previousDate, selectedDate) == 0 && days == 0)
                        createEvent(selectedDate);
                    else
                        Toast.makeText(getApplicationContext(), "Hãy điểm danh đúng ngày", Toast.LENGTH_LONG).show();
                }
            }
        });

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Event>> callEvent = apiInterface.getAttendanceList("Bearer " + user.getAccess_token());
        callEvent.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.code() == 200) {
                    mEventList = response.body();
                    for (Event e : mEventList) {
                        e.setCompleted(e.getFinish() == 1 ? true : false);
                        Timestamp timestamp = Timestamp.valueOf(e.getCheckin());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(timestamp.getTime());
                        e.setDate(calendar);
                        mCalendarView.addCalendarObject(parseCalendarObject(e));
                    }

                    if (getSupportActionBar() != null) {
                        int month = mCalendarView.getCurrentDate().get(Calendar.MONTH);
                        int year = mCalendarView.getCurrentDate().get(Calendar.YEAR);
                        getSupportActionBar().setTitle(mShortMonths[month]);
                        getSupportActionBar().setSubtitle(Integer.toString(year));
                    }

                    mCalendarDialog = CalendarDialog.Builder.instance(activity)
                            .setEventList(mEventList)
                            .setOnItemClickListener(new CalendarDialog.OnCalendarDialogListener() {
                                @Override
                                public void onEventClick(Event event) {
                                    onEventSelected(event);
                                }

                                @Override
                                public void onCreateEvent(Calendar calendar) {
                                    createEvent(calendar);
                                }
                            })
                            .create();

                }
                else
                    Toast.makeText(getApplicationContext(), "Xảy ra lỗi trạng thái server" + response.body().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

                Log.d("test pro activity onFailure call: ", call.toString());
                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                Toast.makeText(getApplicationContext(), "Xảy ra lỗi kết nối server getAttendanceList" + call.toString() + t.getMessage(), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }

    private void onEventSelected(Event event) {
        Activity context = CalendarViewWithNotesActivity.this;
        Intent intent = CreateEventActivity.makeIntent(context, event);

        startActivityForResult(intent, CREATE_EVENT_REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );
    }

    private void createEvent(Calendar selectedDate) {

        Log.i("test createEvent", "ahihi");
        Activity context = CalendarViewWithNotesActivity.this;
        Intent intent = CreateEventActivity.makeIntent(context, selectedDate);
        Log.i("test createEvent", "ahihi123");
        startActivityForResult(intent, CREATE_EVENT_REQUEST_CODE);
        overridePendingTransition( R.anim.slide_in_up, R.anim.stay );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_calendar_view, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_today: {
                mCalendarView.setSelectedDate(Calendar.getInstance());

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_EVENT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int action = CreateEventActivity.extractActionFromIntent(data);
                Event event = CreateEventActivity.extractEventFromIntent(data);

                switch (action) {
                    case CreateEventActivity.ACTION_CREATE: {
                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<List<Event>> create = apiInterface.createAttendanceIn("Bearer " + user.getAccess_token(), event.getTitle());
                        create.enqueue(new Callback<List<Event>>() {
                            @Override
                            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                                if(response.code() == 200) {
                                    mEventList = response.body();
                                    for (Event e : mEventList) {
                                        e.setCompleted(e.getFinish() == 1 ? true : false);
                                        Timestamp timestamp = Timestamp.valueOf(e.getCheckin());
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.setTimeInMillis(timestamp.getTime());
                                        e.setDate(calendar);
                                        mCalendarView.addCalendarObject(parseCalendarObject(e));
                                    }
                                    mCalendarDialog.setEventList(mEventList);
                                }
                                else if(response.code() == 302)
                                    Toast.makeText(getApplicationContext(), "Đây không phải giờ làm việc", Toast.LENGTH_LONG).show();
                                else if(response.code() == 301)
                                    Toast.makeText(getApplicationContext(), "Đây không phải ca của bạn", Toast.LENGTH_LONG).show();
                                else if(response.code() == 202)
                                    Toast.makeText(getApplicationContext(), "Quá giờ điểm danh", Toast.LENGTH_LONG).show();
                                else if(response.code() == 201)
                                    Toast.makeText(getApplicationContext(), "Chưa đến giờ điểm danh", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(), "Xảy ra lỗi trạng thái server" + response.body().toString(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(Call<List<Event>> call, Throwable t) {

                                Log.d("test pro activity onFailure call: ", call.toString());
                                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                                Toast.makeText(getApplicationContext(), "Xảy ra lỗi kết nối server createAttendanceIn" + call.toString() + t.getMessage(), Toast.LENGTH_LONG).show();
                                call.cancel();
                            }
                        });
                        break;
                    }
                    case CreateEventActivity.ACTION_EDIT: {
                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<List<Event>> callEvent = apiInterface.createAttendanceOut("Bearer " + user.getAccess_token());
                        callEvent.enqueue(new Callback<List<Event>>() {
                            @Override
                            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                                if(response.code() == 200) {
                                    mEventList = response.body();
                                    for (Event e : mEventList) {
                                        e.setCompleted(e.getFinish() == 1 ? true : false);
                                        Timestamp timestamp = Timestamp.valueOf(e.getCheckin());
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.setTimeInMillis(timestamp.getTime());
                                        e.setDate(calendar);
                                        mCalendarView.addCalendarObject(parseCalendarObject(e));
                                    }
                                        mCalendarDialog.setEventList(mEventList);
                                }
                                else if(response.code() == 303)
                                    Toast.makeText(getApplicationContext(), "Hết hạn checkout", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(), "Xảy ra lỗi trạng thái server" + response.body().toString() + response.code(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(Call<List<Event>> call, Throwable t) {

                                Log.d("test pro activity onFailure call: ", call.toString());
                                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                                Toast.makeText(getApplicationContext(), "Xảy ra lỗi kết nối server createAttendanceOut" + call.toString() + t.getMessage(), Toast.LENGTH_LONG).show();
                                call.cancel();
                            }
                        });
                        break;
                    }
//                    case CreateEventActivity.ACTION_DELETE: {
//                        Event oldEvent = null;
//                        for (Event e : mEventList) {
//                            if (Objects.equals(event.getID(), e.getID())) {
//                                oldEvent = e;
//                                break;
//                            }
//                        }
//                        if (oldEvent != null) {
//                            mEventList.remove(oldEvent);
//                            mCalendarView.removeCalendarObjectByID(parseCalendarObject(oldEvent));
//
//                            mCalendarDialog.setEventList(mEventList);
//                        }
//                        break;
//                    }
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static int diffYMD(Calendar date1, Calendar date2) {
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
                date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH))
            return 0;

        return date1.before(date2) ? -1 : 1;
    }

    private static CalendarView.CalendarObject parseCalendarObject(Event event) {
        return new CalendarView.CalendarObject(
                event.getID(),
                event.getDate(),
                event.getColor(),
                event.isCompleted() ? Color.TRANSPARENT : Color.RED);
    }

}
