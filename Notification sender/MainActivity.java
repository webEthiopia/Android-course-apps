package com.example.sampleapp;

import android.app.Notification;
import android.app.NotificationChannel; // Import for NotificationChannel
import android.app.NotificationManager; // Import for NotificationManager
import android.app.PendingIntent;
import android.content.Context; // Import for Context
import android.content.Intent;
import android.content.pm.PackageManager; // Import for PackageManager (for permissions)
import android.os.Build; // Import for Build version checks
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast; // For showing toasts

import androidx.annotation.NonNull; // For permission result annotation
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat; // For requesting permissions
import androidx.core.app.NotificationCompat; // Import for NotificationCompat.Builder
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest; // Import for Manifest.permission.POST_NOTIFICATIONS

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "my_app_general_channel";
    private static final String CHANNEL_NAME = "General Notifications";
    private static final String CHANNEL_DESCRIPTION = "Notifications for general app alerts";
    private static final int NOTIFICATION_ID = 1; // A unique ID for your notification
    private static final int REQUEST_CODE_POST_NOTIFICATIONS = 101; // Request code for notification permission

    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View rootView = findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (view, insets) -> {
            Insets systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom);
            return insets;
        });

        // Create the notification channel when the app starts (or at least before showing a notification)
        createNotificationChannel();

        btn = findViewById(R.id.button); // No need for casting with findViewById

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check for notification permission before showing the notification
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                                showMyNotification();
                            } else {
                                // Request the permission if not granted
                                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_POST_NOTIFICATIONS);
                            }
                        } else {
                            // For Android versions below 13, POST_NOTIFICATIONS is not a runtime permission
                            // Check if notifications are generally enabled for the app
                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            if (notificationManager != null && notificationManager.areNotificationsEnabled()) {
                                showMyNotification();
                            } else {
                                Toast.makeText(MainActivity.this, "Notifications are disabled for this app. Please enable them in settings.", Toast.LENGTH_LONG).show();
                                // Optionally, you can direct the user to app settings
                                // Intent settingsIntent = new Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                //         .putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, getPackageName());
                                // startActivity(settingsIntent);
                            }
                        }
                    }
                }
        );
    }

    // Method to create the notification channel
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT // or IMPORTANCE_HIGH, IMPORTANCE_LOW, etc.
            );
            channel.setDescription(CHANNEL_DESCRIPTION);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    // Method to show the notification
    private void showMyNotification() {
        Intent intent = new Intent(this, MainActivity.class); // Intent to open MainActivity when clicked
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pInt = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE for Android 12+
        );

        // Build the notification using NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info) // **REQUIRED** small icon
                .setContentTitle("Sample Notification") // Title of the notification
                .setContentText("This is a test notification from your app.") // Content text
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Set priority for older Android versions
                .setContentIntent(pInt) // Set the PendingIntent for when the notification is clicked
                .setAutoCancel(true) // Dismiss the notification when the user taps it
                .setTicker("TickerTitle"); // Ticker text (may not be shown on newer Android versions)

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, builder.build()); // Show the notification
            Toast.makeText(this, "Notification sent!", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_POST_NOTIFICATIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, now show the notification
                showMyNotification();
            } else {
                // Permission denied
                Toast.makeText(this, "Notification permission denied. Cannot show notification.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
