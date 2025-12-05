package com.tezov.koin_sample.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showLoginDialog by remember { mutableStateOf(false) }

    Column(modifier.padding(24.dp)) {

        Text("Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator()
            return
        }

        if (!state.isConnected) {
            Button(onClick = { showLoginDialog = true }) {
                Text("Connect")
            }

            if (showLoginDialog) {
                LoginDialog(
                    onNormalUser = {
                        viewModel.loginNormal()
                        showLoginDialog = false
                    },
                    onAdminUser = {
                        viewModel.loginAdmin()
                        showLoginDialog = false
                    },
                    onCancel = { showLoginDialog = false }
                )
            }

            return
        }

        val scopedClass = remember {
            viewModel.scopedClass()
        }

        ProfileCard(state.name, scopedClass?.value)
        Spacer(Modifier.height(16.dp))
        Text("Role: ${state.role}")
        Spacer(Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
            onClick = { viewModel.logout() }
        ) {
            Text("Disconnect")
        }
    }
}

@Composable
fun LoginDialog(
    onNormalUser: () -> Unit,
    onAdminUser: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Choose user type") },
        text = { Text("Select how you want to connect:") },
        confirmButton = {
            Column {
                TextButton(onClick = onNormalUser) { Text("Normal User") }
                TextButton(onClick = onAdminUser) { Text("Admin User") }
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text("Cancel") }
        }
    )
}

@Composable
fun ProfileCard(
    name: String,
    role: String?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.firstOrNull()?.uppercase() ?: "?",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Column {
                Text(name, style = MaterialTheme.typography.titleLarge)
                Text(
                    "Account owner + $role",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
