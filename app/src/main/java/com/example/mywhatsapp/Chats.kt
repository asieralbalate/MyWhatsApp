package com.example.mywhatsapp

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun Chats() {
    VistaContacto()
}

data class Contacto(
    var grupo: String,
    var nombre : String,
    @DrawableRes var photo: Int
)

fun getContacto(): List<Contacto> {
    return listOf(
        Contacto(
            "Departamento Informática",
            "Jefe de Departamento",
            R.drawable.photo
        ),
        Contacto(
            "Departamento Informática",
            "Tutora DAM",
            R.drawable.photo1
        ),
        Contacto(
            "Departamento Informática",
            "Tutor DAW",
            R.drawable.photo2
        ),
        Contacto(
            "Departamento Informática",
            "Tutora ASIX",
            R.drawable.photo3
        ),
        Contacto(
            "Comunidad Propietarios",
            "Presidenta",
            R.drawable.photo4
        ),
        Contacto(
            "Comunidad Propietarios",
            "Secretaria",
            R.drawable.photo5
        ),
        Contacto(
            "Comunidad Propietarios",
            "Administrador",
            R.drawable.photo6
        ),
        Contacto(
            "Gimnasio",
            "Entrenadora",
            R.drawable.photo7
        ),
        Contacto(
            "Gimnasio",
            "Nutricionista",
            R.drawable.photo8
        ),
        Contacto(
            "Gimnasio",
            "Fisioterapeuta",
            R.drawable.photo9
        )
    )
}

@Composable
fun MyDropDownMenu(showMenu: Boolean, onShowMenuChange: (Boolean) -> Unit){
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { onShowMenuChange(false) },
        Modifier.width(150.dp)
    ) {
        DropdownMenuItem(
            text = { Text(text = "Salir del grupo", color = Color.Black) },
            onClick = { /*TODO*/ })
        DropdownMenuItem(
            text = { Text(text = "Info. del grupo", color = Color.Black) },
            onClick = { /*TODO*/ })
        DropdownMenuItem(
            text = { Text(text = "Crear acceso directo", color = Color.Black) },
            onClick = { /*TODO*/ })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("ResourceType")
@Composable
fun VistaContacto(){
    val contactos : Map<String, List<Contacto>> = getContacto().groupBy { it.grupo }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        contactos.forEach { (items, miContacto) ->
            stickyHeader {
                Text(
                    text = items,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    fontSize = 16.sp
                )
            }
            items(miContacto) { contacto ->
                LineaContacto(contacto = contacto)
            }
        }
    }

}

@Composable
fun LineaContacto(contacto: Contacto){
    val scope = rememberCoroutineScope()
    var showMenu by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.pointerInput(true) {
            detectTapGestures(
                onLongPress = {
                    scope.launch {
                        showMenu = !showMenu
                    }
                }
            )
        }){
        MyDropDownMenu(showMenu) {showMenu = it}
        Image(
            painter = painterResource(id = contacto.photo),
            contentDescription = "Avatar",
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                .size(75.dp)
                .clip(shape = RoundedCornerShape(40.dp)),
            contentScale = ContentScale.Crop,
        )
        Text(text = contacto.nombre,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.padding(start = 10.dp))
    }
}