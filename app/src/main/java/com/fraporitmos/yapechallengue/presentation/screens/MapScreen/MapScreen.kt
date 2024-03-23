package com.fraporitmos.yapechallengue.presentation.screens.MapScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.fraporitmos.yapechallengue.presentation.theme.dimens
import com.fraporitmos.yapechallengue.R
import com.fraporitmos.yapechallengue.presentation.screens.MapScreen.composables.InfoWindow
import com.fraporitmos.yapechallengue.presentation.screens.MapScreen.composables.MapUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("SuspiciousIndentation")
@Composable
fun MapScreen(
    markerInfo: MarkerInfo,
    navController: NavHostController
) {
    val currentLoc = LatLng(markerInfo.latitude, markerInfo.longitude)
    val cameraState = rememberCameraPositionState()
    LaunchedEffect(key1 = currentLoc) {
        cameraState.centerOnLocation(currentLoc)
    }
    val mapProperties = MapProperties(
        mapStyleOptions = MapStyleOptions(MapUtils.getMapStyle()),
        minZoomPreference = 5.4f)
    Box{
        Box(
            modifier = Modifier.padding(
                top = MaterialTheme.dimens.medium3, start = 16.dp,
            ).zIndex(1f)

            ) {
            Icon(
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .width(MaterialTheme.dimens.iconSize)
                    .height(MaterialTheme.dimens.iconSize)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(8.dp),
                tint =    Color.White,
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
            )
        }
        GoogleMap(
            properties = mapProperties,
            cameraPositionState = cameraState,
            modifier = Modifier.fillMaxSize(),
            uiSettings = MapUiSettings(scrollGesturesEnabled = false, zoomGesturesEnabled = false, zoomControlsEnabled = false),

            ) {

            val icon = MapUtils.bitmapDescriptor(
                LocalContext.current, R.drawable.marker
            )
            MarkerInfoWindow(
                state = MarkerState(position = LatLng(markerInfo.latitude.toDouble(), markerInfo.longitude.toDouble())),
                icon = icon,
                visible = true,

                ) {
                InfoWindow(recipe =markerInfo)
            }
        }
    }
}

private suspend fun CameraPositionState.centerOnLocation(
    location: LatLng
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        location,
        15f
    ),
    durationMs = 1500
)


