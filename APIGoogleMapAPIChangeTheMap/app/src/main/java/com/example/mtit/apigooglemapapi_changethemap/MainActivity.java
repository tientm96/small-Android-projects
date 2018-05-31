package com.example.mtit.apigooglemapapi_changethemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    /*Chỉ khai báo để có đối tượng gọi trong hàm implement. Hàm này tự động được gọi khi class Implement được gọi đến*/
    GoogleMap map;


    /*Search "google map android api" chọn https://developers.google.com/maps/documentation/android-api/?hl=vi
    *   + ở phần OVERVIEW có hướng dẫn add Marker(đánh dấu tọa độ), làm theo trong đấy.
    *   + tự khai báo thêm map.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2 dòng khai báo fragment copy về:
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this); /*This: gọi đến class Implements*/
    }


    /*method implement: copy code về, method này sẽ tự động được gọi khi class Implement đc gọi.*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap; // 1 dòng này tự gán sau khi khai báo toàn cục.

        /*Tọa độ (vĩ độ latitude, kinh độ longitude) của địa chỉ muốn trỏ Marker vào. Search tọa độ rồi đưa vào cũng được.*/
//        LatLng coordinates = new LatLng(-33.867, 151.206);

        /*Vào trang map, chọn địa điểm mình muốn, phóng to hết cỡ cho chính xác nhất tọa độ của địa điểm đó.
        * Trên thanh link có đoạn chứa thông tin tọa độ, hoặc kích phải chọn "Đây là gì" thì sẽ cho ra thông tin tọa độ
        * ở dưới cùng*/
        LatLng coordinates = new LatLng(10.961106, 106.789952);



        /*Camera: là góc độ nhìn; 20: Zoom mặc định khi vừa mở app, số càng lớn thì vừa mở app lên ta thấy càng rõ.*/
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 16));




        //TODO: ----------------------GỌI CÁC HÀM ĐẶC TÍNH--------------------------
        /*Hàm tạo marker*/
        HelloMapMarker(map, coordinates);

        /*Hàm đổi loại map*/
//        ChangeTheMapType(map);        //Đóng lại vì nếu ko phải Type = normal thì sẽ ko hiện lên tác dụng của hàm Styling

        /*Hàm chuyển đổi loại marker*/
        CustomMarkersandInfowindow(coordinates);

        /*Hàm đổi style của map: style chỉ chạy được nếu Maptype = Normal. Còn khác thì phải đóng ChangTheMapType lại*/
        Styling(map);

        /*flat marker: mũi tên nằm ngang di chuyển như đang chỉ đường*/
        FlatMarkers(map, coordinates);

        /*tạo ra 1 đường gấp khúc đi qua tất cả các điểm tọa độ*/
        Polylines(map);
    }



    //CÁC HÀM TỰ TẠO---------------------------------

    private void HelloMapMarker(GoogleMap map, LatLng coordinates){
        /*Xin quyền, tạm thời ko dùng đến*/
        //map.setMyLocationEnabled(true);

        /*TODO: Marker: Là thêm vào chấm đỏ đỏ trỏ ở tọa độ. */
        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(coordinates)
        );
    }

    private void ChangeTheMapType(GoogleMap map){
        // Other supported types include: MAP_TYPE_NORMAL, MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    private void CustomMarkersandInfowindow(LatLng coordinates){
        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_blue_home))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(coordinates)
        );
    }


    /*Lấy Json của style tại https://mapstyle.withgoogle.com.
    * Ở Res tạo mới raw -> new File -> chọn tạo file Json
    * Paste json về file json trong raw.
    * */
    private void Styling(GoogleMap map){
        // Customise the styling of the base map using a JSON object defined
        // in a raw resource file.
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(
                this, R.raw.style_json);
        map.setMapStyle(style);
    }

    private void FlatMarkers(GoogleMap map, LatLng coordinates){

        // Flat markers will rotate when the map is rotated,
        // and change perspective when the map is tilted.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.direction_arrow))
                .position(coordinates)
                .flat(true)
                .rotation(245));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(coordinates)
                .zoom(13)
                .bearing(90)
                .build();

        // Animate the change in camera view over 2 seconds
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                2000, null);
    }


    /*tạo ra 1 đường gấp khúc đi qua tất cả các điểm tọa độ*/
    private void Polylines(GoogleMap map){
        map.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(-33.866, 151.195))  // Sydney
                .add(new LatLng(-18.142, 178.431))  // Fiji
                .add(new LatLng(21.291, -157.821))  // Hawaii
                .add(new LatLng(37.423, -122.091))  // Mountain View
        );
    }


}
