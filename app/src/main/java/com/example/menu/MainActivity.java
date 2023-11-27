package com.example.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ListView listView;
    private Button popupButton;
    private MapView mapView;
    private GoogleMap googleMap;
    private Marker randomMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        listView = findViewById(R.id.listView);
        popupButton = findViewById(R.id.popupButton);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        List<LatLng> locations = new ArrayList<>();
        locations.add(new LatLng(23.239361, 69.719009)); // Bengaluru
        locations.add(new LatLng(10.016075, 77.032753)); // Sample Location 1
        locations.add(new LatLng(18.993652, 72.825218)); // Sample Location 2

        // Initialize the list view with some sample data
        String[] restaurantItems = {"HOTELS", "COFFEE SHOPS", "COLLEGES", "MALLS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, restaurantItems);
        listView.setAdapter(adapter);

        // Register the list view for the context menu
        registerForContextMenu(listView);

        // Set click listener for the popup button
        popupButton.setOnClickListener(v -> showPopupMenu(v));
    }

    // Creating the context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    // Handling context menu item clicks
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        String selectedItem = listView.getItemAtPosition(position).toString();

        if (item.getItemId() == R.id.item_order) {
            if (selectedItem == "HOTELS") {
                List<LatLng> locations = new ArrayList<>();
                locations.add(new LatLng(12.924767, 77.615069)); // Bengaluru
                locations.add(new LatLng(12.9246255, 77.6150777)); // Sample Location 1
                locations.add(new LatLng(12.931061, 77.610667)); // Sample Location 2

                googleMap.clear(); // Clear existing markers
                for (LatLng location : locations) {
                    googleMap.addMarker(new MarkerOptions().position(location).title("Location"));
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (LatLng location : locations) {
                    builder.include(location);
                }
                LatLngBounds bounds = builder.build();
                int padding = 100; // Padding in pixels for the area around the markers
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));

            }
            if (selectedItem == "COFFEE SHOPS") {
                List<LatLng> locations = new ArrayList<>();
                locations.add(new LatLng(12.9345173, 77.6192441)); // Bengaluru
                locations.add(new LatLng(12.9343081, 77.5967136)); // Sample Location 1
                locations.add(new LatLng(12.9290693, 77.6044384)); // Sample Location 2

                googleMap.clear(); // Clear existing markers
                for (LatLng location : locations) {
                    googleMap.addMarker(new MarkerOptions().position(location).title("Location"));
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (LatLng location : locations) {
                    builder.include(location);
                }
                LatLngBounds bounds = builder.build();
                int padding = 100; // Padding in pixels for the area around the markers
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));

            }

            if (selectedItem == "COLLEGES") {
                List<LatLng> locations = new ArrayList<>();
                locations.add(new LatLng(12.9311124, 77.5877713)); // Bengaluru
                locations.add(new LatLng(12.9311124, 77.5877713)); // Sample Location 1
                locations.add(new LatLng(12.9058685, 77.4825361)); // Sample Location 2

                googleMap.clear(); // Clear existing markers
                for (LatLng location : locations) {
                    googleMap.addMarker(new MarkerOptions().position(location).title("Location"));
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (LatLng location : locations) {
                    builder.include(location);
                }
                LatLngBounds bounds = builder.build();
                int padding = 100; // Padding in pixels for the area around the markers
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));

            }

            if (selectedItem == "MALLS") {
                List<LatLng> locations = new ArrayList<>();
                locations.add(new LatLng(12.9344351, 77.5433606)); // Bengaluru
                locations.add(new LatLng(12.9344351, 77.5433606)); // Sample Location 1
                locations.add(new LatLng(12.9147341, 77.5702256)); // Sample Location 2

                googleMap.clear(); // Clear existing markers
                for (LatLng location : locations) {
                    googleMap.addMarker(new MarkerOptions().position(location).title("Location"));
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (LatLng location : locations) {
                    builder.include(location);
                }
                LatLngBounds bounds = builder.build();
                int padding = 100; // Padding in pixels for the area around the markers
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));

            }
            showToast("Viewing" + selectedItem + "!");
            return true;

        }

        else {
            return super.onContextItemSelected(item);
        }
    }
    private void unpointMarker() {
        if (randomMarker != null) {
            randomMarker.remove();
            randomMarker = null;
        }
    }
    // Creating options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    // Handling options menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this App!");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "I found a great Location Viewer!");

            Intent chooser = Intent.createChooser(shareIntent, "Share via");

            // Add share options for specific apps
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "I found a great Location Viewer!");
            whatsappIntent.setPackage("com.whatsapp");

            Intent snapchatIntent = new Intent(Intent.ACTION_SEND);
            snapchatIntent.setType("text/plain");
            snapchatIntent.putExtra(Intent.EXTRA_TEXT, "I found a great Location Viewer!");
            snapchatIntent.setPackage("com.snapchat.android");

            Intent facebookIntent = new Intent(Intent.ACTION_SEND);
            facebookIntent.setType("text/plain");
            facebookIntent.putExtra(Intent.EXTRA_TEXT, "I found a great Location Viewer!");
            facebookIntent.setPackage("com.facebook.katana");

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this Tracker app!");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "I found a great Location Viewer!");

            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { whatsappIntent, snapchatIntent, facebookIntent, emailIntent });

            startActivity(chooser);

            showToast("Sharing the Location!");
            return true;
        } else if (item.getItemId() == R.id.item_settings) {
            showToast("LOGOUT");
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Showing the popup menu
    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_item_call_waiter) {
                googleMap.clear();
                return true;
            } else if (item.getItemId() == R.id.menu_item_reserve_table) {
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));
                return true;
            } else {
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Generate random latitude and longitude
        double latitude = 12.930927;
        double longitude = 77.630990;

        // Add a marker at the random location
        LatLng randomLocation = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(randomLocation).title("Random Location"));

        // Move the camera to the random location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(randomLocation, 15.0f));
    }

    // Helper method to generate a random double within a specified range
    private double getRandomDouble(double min, double max) {
        return new Random().nextDouble() * (max - min) + min;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
