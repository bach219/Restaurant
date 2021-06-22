package com.quintus.labs.grocerystore.helper;

import com.quintus.labs.grocerystore.model.Category;
import com.quintus.labs.grocerystore.model.Offer;
import com.quintus.labs.grocerystore.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class Data {
    List<Category> categoryList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<Product> newList = new ArrayList<>();
    List<Product> popularList = new ArrayList<>();
    List<Offer> offerList = new ArrayList<>();

    public List<Category> getCategoryList() {
        Category category = new Category("1", "Đồ uống", "https://t4.ftcdn.net/jpg/01/13/79/77/240_F_113797742_2I5CiuaExm5TehADSFgjvaGqaK60j4gb.jpg");
        categoryList.add(category);
         category = new Category("2", "Bánh mì", "https://image.flaticon.com/icons/png/512/454/454568.png");
        categoryList.add(category);
         category = new Category("3", "Xôi", "https://image.flaticon.com/icons/png/512/3946/3946804.png");
        categoryList.add(category);
         category = new Category("4", "Ăn vặt", "https://image.flaticon.com/icons/png/512/3480/3480708.png");
        categoryList.add(category);
//        category = new Category("2", "Home & Cleaning", "https://lisasnatural.com/wp-content/uploads/2018/05/housecleanicon-300x228.jpg");
//        categoryList.add(category);
//        category = new Category("3", "Baby Care", "https://tips4tots.files.wordpress.com/2015/08/medical-insurance-free-icon.png");
//        categoryList.add(category);
//        category = new Category("4", "sports & Nutrition", "https://kathleenhalme.com/images/nutrition-clipart-sport.jpg");
//        categoryList.add(category);
//        category = new Category("5", "Pet care", "http://kasperstromman.files.wordpress.com/2013/05/dog-cat-above-board.jpg");
//        categoryList.add(category);
//        category = new Category("6", "Health & Household", "https://thumbs.dreamstime.com/b/household-cleaning-products-accessories-basket-there-mop-detergents-rubber-gloves-glass-cleaner-sponges-89944820.jpg");
//        categoryList.add(category);
        return categoryList;
    }

    public List<Product> getProductList() {
        Product product = new Product("1", "2", "Bánh mì như ý", "", "1 suất", "VNĐ.", "20000", "", "https://images.foody.vn/res/g77/769242/prof/s576x330/foody-upload-api-foody-mobile-057bb011889c69c2308d-180822162906.jpg");
        productList.add(product);
        product = new Product("2", "1", "Trà chanh", "", "1 ly", "VNĐ.", "10000", "", "https://www.unileverfoodsolutions.com.vn/dam/global-ufs/mcos/phvn/vietnam/calcmenu/recipes/VN-recipes/other/energizing-lemon-tea/main-header.jpg");
        productList.add(product);
        product = new Product("3", "2", "Nước cam", "", "1 ly", "VNĐ.", "15000", "", "https://photo-cms-plo.zadn.vn/Uploaded/2021/ycivopcg/2020_04_04/hinh-cam-vat_ltvp.jpg");
        productList.add(product);
        product = new Product("4", "3", "Xôi xá xíu", "", "1 suất", "VNĐ.", "25000", "", "https://1.bp.blogspot.com/-USUIhPdsJqY/W85WoGlyHKI/AAAAAAAALwY/sQwZecIa9HQ0O7Q2UZb5B-BTI1nXY2yNgCEwYBhgL/s1600/IMG_2059.JPG");
        productList.add(product);
        product = new Product("5", "3", "Xôi xéo", "", "1 suất", "VNĐ.", "10000", "", "https://xebanhmithonhiky.vn/wp-content/uploads/2020/09/cach-lam-xoi-xeo-de-ban.jpg");
        productList.add(product);
        product = new Product("6", "4", "Khoai tây chiên ", "", "1 suất", "VNĐ.", "20000", "", "https://4.bp.blogspot.com/-rN6xH0gyi8g/XlEh_Wab9qI/AAAAAAAACAg/JQoE5WtBVPENqXSoAkGjF3bjQYyj-E2XgCK4BGAYYCw/s640/unnamed%2B%25281%2529.jpg");
        productList.add(product);
        product = new Product("7", "4", "Xúc xích", "", "1 cái", "VNĐ.", "10000", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIIKlXC4DF4ORsIGGJUbmdlypA4pyUwTxLdQ&usqp=CAU");
        productList.add(product);
//        Product product = new Product("1", "1", "Apple", "", "1 Kg", "Rs.", "20", "10% OFF", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("2", "1", "Banana", "", "1 Bounch", "Rs.", "10", "20% OFF", "https://images-na.ssl-images-amazon.com/images/I/21DejQuoT2L.jpg");
//        productList.add(product);
//        product = new Product("3", "2", "House Clean Liquid", "", "1 Lit.", "Rs.", "25", "", "http://sunsetcleaningcia.com/wp-content/uploads/2016/05/houseclean.png");
//        productList.add(product);
//        product = new Product("4", "2", "House Clean Brush", "", "1 Piece", "Rs.", "10", "", "https://www.clean-hoouse.com/wp-content/uploads/2017/09/13.png");
//        productList.add(product);
//        product = new Product("5", "3", "Pampers", "", "1 Piece", "Rs.", "20", "10% OFF", "https://cdn.bmstores.co.uk/images/hpcProductImage/imgFull/311448-Pampers-Baby-Dry-Size-4-Maxi-251.jpg");
//        productList.add(product);
//        product = new Product("6", "3", "Baby Oil", "", "500 Ml", "Rs.", "31", "", "https://www.fortunaonline.net/media/catalog/product/cache/1/small_image/295x/040ec09b1e35df139433887a97daa66f/n/k/nkbcp12_-_xia-shib-ao-baby-oil-200ml.png");
//        productList.add(product);
//        product = new Product("7", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("8", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("9", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("10", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("11", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("12", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("13", "1", "Litche", "", "1 Kg", "Rs.", "20", "30%OFF", "https://cdn.shopify.com/s/files/1/0665/4989/products/lichee-485x400_grande.jpg");
//        productList.add(product);

//        Product product = new Product("1", "1", "Apple", "", "1 Kg", "Rs.", "20", "10% OFF", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("2", "1", "Banana", "", "1 Bounch", "Rs.", "10", "20% OFF", "https://images-na.ssl-images-amazon.com/images/I/21DejQuoT2L.jpg");
//        productList.add(product);
//        product = new Product("3", "2", "House Clean Liquid", "", "1 Lit.", "Rs.", "25", "", "http://sunsetcleaningcia.com/wp-content/uploads/2016/05/houseclean.png");
//        productList.add(product);
//        product = new Product("4", "2", "House Clean Brush", "", "1 Piece", "Rs.", "10", "", "https://www.clean-hoouse.com/wp-content/uploads/2017/09/13.png");
//        productList.add(product);
//        product = new Product("5", "3", "Pampers", "", "1 Piece", "Rs.", "20", "10% OFF", "https://cdn.bmstores.co.uk/images/hpcProductImage/imgFull/311448-Pampers-Baby-Dry-Size-4-Maxi-251.jpg");
//        productList.add(product);
//        product = new Product("6", "3", "Baby Oil", "", "500 Ml", "Rs.", "31", "", "https://www.fortunaonline.net/media/catalog/product/cache/1/small_image/295x/040ec09b1e35df139433887a97daa66f/n/k/nkbcp12_-_xia-shib-ao-baby-oil-200ml.png");
//        productList.add(product);
//        product = new Product("7", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("8", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("9", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("10", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("11", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("12", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("13", "1", "Litche", "", "1 Kg", "Rs.", "20", "30%OFF", "https://cdn.shopify.com/s/files/1/0665/4989/products/lichee-485x400_grande.jpg");
//        productList.add(product);
        return productList;
    }

    public List<Product> getNewList() {
//        Product product = new Product("1", "1", "Apple", "", "1 Kg", "Rs.", "20", "10% OFF", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        newList.add(product);
//        product = new Product("2", "1", "Banana", "", "1 Bounch", "Rs.", "10", "20% OFF", "https://images-na.ssl-images-amazon.com/images/I/21DejQuoT2L.jpg");
//        newList.add(product);
//        product = new Product("3", "2", "House Clean Liquid", "", "1 Lit.", "Rs.", "25", "", "http://sunsetcleaningcia.com/wp-content/uploads/2016/05/houseclean.png");
//        newList.add(product);
//        product = new Product("4", "2", "House Clean Brush", "", "1 Piece", "Rs.", "10", "", "https://www.clean-hoouse.com/wp-content/uploads/2017/09/13.png");
//        newList.add(product);
//        product = new Product("5", "3", "Pampers", "", "1 Piece", "20", "Rs.", "10% OFF", "https://cdn.bmstores.co.uk/images/hpcProductImage/imgFull/311448-Pampers-Baby-Dry-Size-4-Maxi-251.jpg");
//        newList.add(product);

        Product product = new Product("1", "2", "Bánh mì như ý", "", "1 suất", "VNĐ.", "20000", "", "https://images.foody.vn/res/g77/769242/prof/s576x330/foody-upload-api-foody-mobile-057bb011889c69c2308d-180822162906.jpg");
        newList.add(product);
        product = new Product("2", "1", "Trà chanh", "", "1 ly", "VNĐ.", "10000", "", "https://www.unileverfoodsolutions.com.vn/dam/global-ufs/mcos/phvn/vietnam/calcmenu/recipes/VN-recipes/other/energizing-lemon-tea/main-header.jpg");
        newList.add(product);
        product = new Product("3", "2", "Nước cam", "", "1 ly", "VNĐ.", "15000", "", "https://photo-cms-plo.zadn.vn/Uploaded/2021/ycivopcg/2020_04_04/hinh-cam-vat_ltvp.jpg");
        newList.add(product);
        product = new Product("4", "3", "Xôi xá xíu", "", "1 suất", "VNĐ.", "25000", "", "https://1.bp.blogspot.com/-USUIhPdsJqY/W85WoGlyHKI/AAAAAAAALwY/sQwZecIa9HQ0O7Q2UZb5B-BTI1nXY2yNgCEwYBhgL/s1600/IMG_2059.JPG");
        newList.add(product);
        product = new Product("5", "3", "Xôi xéo", "", "1 suất", "VNĐ.", "10000", "", "https://xebanhmithonhiky.vn/wp-content/uploads/2020/09/cach-lam-xoi-xeo-de-ban.jpg");
        newList.add(product);
        product = new Product("6", "4", "Khoai tây chiên ", "", "1 suất", "VNĐ.", "20000", "", "https://4.bp.blogspot.com/-rN6xH0gyi8g/XlEh_Wab9qI/AAAAAAAACAg/JQoE5WtBVPENqXSoAkGjF3bjQYyj-E2XgCK4BGAYYCw/s640/unnamed%2B%25281%2529.jpg");
        newList.add(product);
        product = new Product("7", "4", "Xúc xích", "", "1 cái", "VNĐ.", "10000", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIIKlXC4DF4ORsIGGJUbmdlypA4pyUwTxLdQ&usqp=CAU");
        newList.add(product);
        return newList;
    }

    public List<Product> getPopularList() {
//        Product product = new Product("6", "3", "Baby Oil", "", "500 Ml", "Rs.", "31", "", "https://www.fortunaonline.net/media/catalog/product/cache/1/small_image/295x/040ec09b1e35df139433887a97daa66f/n/k/nkbcp12_-_xia-shib-ao-baby-oil-200ml.png");
//        popularList.add(product);
//        product = new Product("7", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        popularList.add(product);
//        product = new Product("8", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        popularList.add(product);
//        product = new Product("9", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        popularList.add(product);
//        product = new Product("10", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        popularList.add(product);
//        product = new Product("11", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        popularList.add(product);
//        product = new Product("12", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        popularList.add(product);
//        product = new Product("13", "1", "Litche", "", "1 Kg", "Rs.", "20", "30%OFF", "https://cdn.shopify.com/s/files/1/0665/4989/products/lichee-485x400_grande.jpg");
//        popularList.add(product);

        Product product = new Product("1", "2", "Bánh mì như ý", "", "1 suất", "VNĐ.", "20000", "", "https://images.foody.vn/res/g77/769242/prof/s576x330/foody-upload-api-foody-mobile-057bb011889c69c2308d-180822162906.jpg");
        popularList.add(product);
        product = new Product("2", "1", "Trà chanh", "", "1 ly", "VNĐ.", "10000", "", "https://www.unileverfoodsolutions.com.vn/dam/global-ufs/mcos/phvn/vietnam/calcmenu/recipes/VN-recipes/other/energizing-lemon-tea/main-header.jpg");
        popularList.add(product);
        product = new Product("3", "2", "Nước cam", "", "1 ly", "VNĐ.", "15000", "", "https://photo-cms-plo.zadn.vn/Uploaded/2021/ycivopcg/2020_04_04/hinh-cam-vat_ltvp.jpg");
        popularList.add(product);
        product = new Product("4", "3", "Xôi xá xíu", "", "1 suất", "VNĐ.", "25000", "", "https://1.bp.blogspot.com/-USUIhPdsJqY/W85WoGlyHKI/AAAAAAAALwY/sQwZecIa9HQ0O7Q2UZb5B-BTI1nXY2yNgCEwYBhgL/s1600/IMG_2059.JPG");
        popularList.add(product);
        product = new Product("5", "3", "Xôi xéo", "", "1 suất", "VNĐ.", "10000", "", "https://xebanhmithonhiky.vn/wp-content/uploads/2020/09/cach-lam-xoi-xeo-de-ban.jpg");
        popularList.add(product);
        product = new Product("6", "4", "Khoai tây chiên ", "", "1 suất", "VNĐ.", "20000", "", "https://4.bp.blogspot.com/-rN6xH0gyi8g/XlEh_Wab9qI/AAAAAAAACAg/JQoE5WtBVPENqXSoAkGjF3bjQYyj-E2XgCK4BGAYYCw/s640/unnamed%2B%25281%2529.jpg");
        productList.add(product);
        product = new Product("7", "4", "Xúc xích", "", "1 cái", "VNĐ.", "10000", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIIKlXC4DF4ORsIGGJUbmdlypA4pyUwTxLdQ&usqp=CAU");
        popularList.add(product);
        return popularList;
    }

    public List<Offer> getOfferList() {
        Offer offer = new Offer("http://1.bp.blogspot.com/-MMt-60IWEdw/VqZsh45Dv8I/AAAAAAAAMHg/70D9tVowlyc/s1600/askmegrocery-republic-day-offer.jpg");
        offerList.add(offer);
        offer = new Offer("http://www.lootkaro.com/wp-content/uploads/2016/05/as1.jpg");
        offerList.add(offer);
        offer = new Offer("https://453xbcknr3t3ahr522mms518-wpengine.netdna-ssl.com/wp-content/themes/iga-west/images/banner-save-more.jpg");
        offerList.add(offer);
        offer = new Offer("https://images-eu.ssl-images-amazon.com/images/G/31/img16/Grocery/SVD/July18/750x375.png");
        offerList.add(offer);
        offer = new Offer("https://images-eu.ssl-images-amazon.com/images/G/31/img16/Grocery/BreakfastStore/kmande_2018-06-15T12-00_f010a5_1121973_grocery_750x375.jpg");
        offerList.add(offer);
        offer = new Offer("http://www.enjoygrocery.com/images/enjoygrocery-offer.jpg");
        offerList.add(offer);


        return offerList;
    }
}
