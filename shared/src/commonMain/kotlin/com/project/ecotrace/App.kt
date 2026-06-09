package com.project.ecotrace

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.FlowRow

// Refined Premium Palette
val ForestGreen = Color(0xFF1B5E20)
val MintGreen = Color(0xFF4CAF50)
val SoftCreamBg = Color(0xFFFBFBFA) // Warm neutral off-white
val PureWhite = Color(0xFFFFFFFF)
val CharcoalGray = Color(0xFF2D312E) // High contrast body text
val MutedGray = Color(0xFF757575)

@Composable
fun App() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SoftCreamBg),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = 480.dp)
                    .background(SoftCreamBg)
            ) {
                MainTrackerScreen()
            }
        }
    }
}

@Composable
fun MainTrackerScreen() {
    val calculator = remember { CarbonCalculator() }

    // UI State Holders
    var transportKm by remember { mutableStateOf(15.0) }
    var transportType by remember { mutableStateOf("Petrol Car") }
    var electricityKwh by remember { mutableStateOf(10.0) }
    var dietType by remember { mutableStateOf("Vegetarian") }

    val currentInput = CarbonInput(transportKm, transportType, electricityKwh, dietType)
    val result = calculator.calculate(currentInput)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp), // Increased spacing to let elements breathe
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // App Header with strict alignment
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Home, contentDescription = null, tint = ForestGreen, modifier = Modifier.size(26.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "EcoTrace",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = ForestGreen
            )
        }

        Text(
            text = "Track and reduce your daily carbon footprint effortlessly.",
            fontSize = 14.sp,
            color = MutedGray,
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            textAlign = TextAlign.Center
        )

        // 🌟 PREMIUM GRADIENT DASHBOARD CARD (Hero Element)
        val gradientBrush = Brush.linearGradient(
            colors = listOf(MintGreen, ForestGreen)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(24.dp),
                    ambientColor = Color.Black.copy(alpha = 0.1f),
                    spotColor = ForestGreen.copy(alpha = 0.25f)
                )
                .background(gradientBrush, shape = RoundedCornerShape(24.dp))
                .clip(RoundedCornerShape(24.dp))
        ) {
            Column(
                modifier = Modifier.padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "YOUR DAILY FOOTPRINT",
                    color = PureWhite.copy(alpha = 0.85f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                val roundedTotal = ((result.totalEmissions * 100).toInt()) / 100.0
                Text(
                    text = buildAnnotatedString {
                        append("$roundedTotal kg CO")
                        withStyle(style = SpanStyle(fontSize = 22.sp, baselineShift = BaselineShift.Subscript)) {
                            append("2")
                        }
                        append("e")
                    },
                    color = PureWhite,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(16.dp))
                Surface(
                    color = PureWhite.copy(alpha = 0.18f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Primary Contributor: ${result.primaryContributor}",
                        color = PureWhite,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // 🚗 TRANSPORTATION SECTION
        InputSectionCard(title = "Transportation", icon = Icons.Default.LocationOn) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Distance Traveled", fontSize = 14.sp, color = CharcoalGray, fontWeight = FontWeight.Medium)
                Text("${transportKm.toInt()} km", fontSize = 14.sp, color = ForestGreen, fontWeight = FontWeight.Bold)
            }
            Slider(
                value = transportKm.toFloat(),
                onValueChange = { transportKm = it.toDouble() },
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = ForestGreen,
                    activeTrackColor = ForestGreen,
                    inactiveTrackColor = ForestGreen.copy(alpha = 0.15f)
                )
            )

            Text("Vehicle Type", fontSize = 13.sp, color = MutedGray, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
            val vehicles = listOf("Petrol Car", "Diesel Car", "Electric Vehicle", "Public Transit", "Motorbike")

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    vehicles.forEach { type ->
                        StyledFilterChip(selected = transportType == type, label = type, onClick = { transportType = type })
                    }
                }
            }
        }

        // ⚡ ENERGY SECTION
        InputSectionCard(title = "Household Energy", icon = Icons.Default.Home) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Electricity Used", fontSize = 14.sp, color = CharcoalGray, fontWeight = FontWeight.Medium)
                Text("${electricityKwh.toInt()} kWh", fontSize = 14.sp, color = ForestGreen, fontWeight = FontWeight.Bold)
            }
            Slider(
                value = electricityKwh.toFloat(),
                onValueChange = { electricityKwh = it.toDouble() },
                valueRange = 0f..50f,
                colors = SliderDefaults.colors(
                    thumbColor = ForestGreen,
                    activeTrackColor = ForestGreen,
                    inactiveTrackColor = ForestGreen.copy(alpha = 0.15f)
                )
            )
        }

        // 🍔 DIETARY HABITS SECTION
        InputSectionCard(title = "Dietary Habits", icon = Icons.Default.ShoppingCart) {
            Text("Diet Regime", fontSize = 13.sp, color = MutedGray, fontWeight = FontWeight.Bold)
            val diets = listOf("Meat Heavy", "Vegetarian", "Vegan")

            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                diets.forEach { type ->
                    StyledFilterChip(selected = dietType == type, label = type, onClick = { dietType = type })
                }
            }
        }

        // 💡 INSIGHTS SECTION
        Text(
            text = "Personalized Actions",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = CharcoalGray,
            modifier = Modifier.padding(top = 8.dp)
        )

        result.recommendations.forEach { tip ->
            Card(
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black.copy(alpha = 0.03f), RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MintGreen,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(
                        text = tip,
                        fontSize = 13.sp,
                        color = CharcoalGray,
                        modifier = Modifier.weight(1f),
                        lineHeight = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun InputSectionCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, content: @Composable ColumnScope.() -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black.copy(alpha = 0.04f), RoundedCornerShape(20.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = ForestGreen, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ForestGreen)
            }
            content()
        }
    }
}

@Composable
fun RowScope.StyledFilterChip(selected: Boolean, label: String, onClick: () -> Unit) {
    // Micro-interactions: Smooth colour transition animation on select
    val animatedBgColor by animateColorAsState(if (selected) ForestGreen else PureWhite)
    val animatedLabelColor by animateColorAsState(if (selected) PureWhite else CharcoalGray)
    val animatedBorderColor by animateColorAsState(if (selected) ForestGreen else Color.Black.copy(alpha = 0.12f))

    Surface(
        modifier = Modifier
            .wrapContentSize() // Tells the container to dynamically scale to fit its inner text
            .weight(1f)
            .height(38.dp)
            .border(1.dp, animatedBorderColor, RoundedCornerShape(10.dp)),
        color = animatedBgColor,
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
            Text(
                text = label,
                color = animatedLabelColor,
                fontSize = 12.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 1 // Forces text to stay on a single line cleanly
            )
        }
    }
}