package com.example.ut3_teoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ToggleableState
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.ut3_teoria.ui.theme.UT3_TeoriaTheme
import kotlin.math.ceil
import kotlin.math.floor

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UT3_TeoriaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    var estadoRadio by rememberSaveable { mutableStateOf("opción 1") }
                    val myOptions =
                        getOptions(listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4"))
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(10.dp)
                    ) {
                        //myOptions.forEach { MyCheckBox(it) }
                        //CheckboxParentExample()
                        MyRadioButton(estadoRadio) { estadoRadio = it }
                        //MyProgressBar()
                        SimpleContinuousSlider()
                        //CircularIndicatorButton()
                        RatingBar()
                        RatingBar(rating = 2.5)
                        RatingBar(stars = 10, rating = 8.5)
                        RatingBar(rating = 5.0)
                        RatingBar(rating = 1.0)
                        RatingBar(rating = 0.0, starsColor = Color.Gray)

                    }

                }
            }
        }
    }
}
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {

    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }

        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }

        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}


@Composable
fun SimpleContinuousSlider() {
    val range = 0f..100f
    var selection by remember { mutableStateOf(50f) }
    val step=3

    Slider(
        value = selection,
        valueRange = range,
        steps = step,
        onValueChange = { selection = it }
    )
    Text(text = selection.toString())
}


@Composable
fun MyProgressBar() {
    var showLoading by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
            LinearProgressIndicator(
                Modifier.padding(top = 32.dp),
                //backgroundColor = Color.Gray,
                color = Color.Red
            )
            LinearProgressIndicator(
                Modifier.padding(top = 32.dp),
                color = ProgressIndicatorDefaults.linearColor,
                trackColor = Color.Red
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar perfil")

        }
    }
}


@Composable
fun MyProgressBar2() {
    CircularProgressIndicator()
}

@Composable
fun CircularIndicatorButton() {
    var loading by remember { mutableStateOf(false) }

    Button(onClick = { loading = true }, enabled = !loading) {
        Text("Start loading")
    }

    if (!loading) return

    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun MyRadioButton(name: String, onItemSelected: (String) -> Unit) {
    val animalTypes = listOf("Todos", "Perro", "Gato", "Aves")
    val opcion = ""
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            animalTypes.forEach { item ->
                RadioButton(selected = name == item, onClick = { onItemSelected(item) })
                Text(text = item)
            }
        }
        Text(text = opcion)
    }
}


@Preview(showBackground = true)
@Composable
fun DragonCard(/*...*/) {

    Column {
        Image(
            painter = painterResource(id = R.drawable.dragon),
            contentDescription = "Dragon Ball",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                //.requiredSize(150.dp)
                .clip(CircleShape)

        )
        /*
                    //Cambio de color, tamaño, cursiva, negrita y alineación centrada
                    Text(
                        text = "Imagen Dragon Ball",
                        style = TextStyle(
                            color = Color.Blue,
                            fontSize = 30.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            shadow = Shadow(color = Color.Red, Offset(10f, 10f), blurRadius = 3f)
                        )
                    )*/


        //TiposTextos()
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Dragon Ball",
            modifier = Modifier
                .requiredSize(150.dp)
                .clip(CircleShape)
                .border(5.dp, color = Color.Red, CircleShape)
            //.clip(RoundedCornerShape(25f))
        )
    }
}

@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = "Icono Estrella",
        tint = Color.Red
    )
}

@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(true) }

    Switch(
        checked = state,
        onCheckedChange = {
            state = it
        },
        thumbContent = if (state) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            null
        }
    )
}

@Composable
fun MyCheckBox(checkInfo: CheckInfo) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}

@Composable
fun CheckboxParentExample() {
    // Initialize states for the child checkboxes
    val childCheckedStates = remember { mutableStateListOf(false, false, false) }

    // Compute the parent state based on children's states
    val parentState = when {
        childCheckedStates.all { it } -> androidx.compose.ui.state.ToggleableState.On
        childCheckedStates.none { it } -> androidx.compose.ui.state.ToggleableState.Off
        else -> androidx.compose.ui.state.ToggleableState.Indeterminate
    }

    Column {
        // Parent TriStateCheckbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Select all")
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    // Determine new state based on current state
                    val newState = parentState != androidx.compose.ui.state.ToggleableState.On
                    childCheckedStates.forEachIndexed { index, _ ->
                        childCheckedStates[index] = newState
                    }
                }
            )
        }

        // Child Checkboxes
        childCheckedStates.forEachIndexed { index, checked ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Option ${index + 1}")
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        // Update the individual child state
                        childCheckedStates[index] = isChecked
                    }
                )
            }
        }
    }

    if (childCheckedStates.all { it }) {
        Text("All options selected")
    }
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var estadoCheck by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = estadoCheck,
            onCheckedChange = { estadoCheck = it }
        )
    }
    /*
    Otra forma
     onCheckedChange = {MyNewStatus -> estadoCheck = MyNewStatus}
    */
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UT3_TeoriaTheme {
        Greeting("Android")
    }
}

@Composable
fun MyText() {
    Column() {
        Text("Texto en compose")
        Text(stringResource(R.string.string_res))
        Text(stringResource(R.string.format_string_res, 2))
        for (item in stringArrayResource(R.array.string_array_res)) {
            Text(item)
        }
    }
}

@Composable
fun FontWeightText() {
    Column {
        Text("Texto con grosor W500", fontWeight = FontWeight.W500)
        Text("Texto con grosor Extra Bold", fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun LetterSpacingText() {
    Column {
        Text("Texto", letterSpacing = 0.15.em)
        Text("Texto", letterSpacing = 0.4.em)
    }
}

@Composable
fun TextDecorationExample() {
    Column {
        Text("Texto", textDecoration = TextDecoration.Underline)
        Text("Texto", textDecoration = TextDecoration.LineThrough)
        Text(
            "Texto",
            textDecoration = TextDecoration.Underline + TextDecoration.LineThrough
        )
    }
}

@Composable
fun LabelAndPlaceHolder() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text(text = "Your Label") },
        placeholder = { Text(text = "Your Placeholder/Hint") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiposTextos() {

    var estadoTextField by remember { mutableStateOf(TextFieldValue("")) }
    val rainbowColors: List<Color> =
        listOf(Color.Magenta, Color(0xFFE03F1A), Color(0xFFE1CB15), Color.Green, Color.Cyan)
    var estadoOutlined by remember { mutableStateOf(TextFieldValue("")) }

    val brush = remember {
        Brush.linearGradient(
            colors = rainbowColors
        )
    }
    TextField(
        value = estadoTextField,
        onValueChange = { estadoTextField = it },
        textStyle = TextStyle(brush = brush)
    )
    OutlinedTextField(
        value = estadoOutlined,
        onValueChange = { estadoOutlined = it },
        label = { Text(text = "Etiqueta") },
        placeholder = { Text("Introduce el texto") },
        modifier = Modifier.padding(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Blue
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "emailIcon"
            )
        },
    )

    /*

    }*/

    /*
       TextField(
            value = estadoTextField,
            onValueChange = { estadoTextField = it },
            placeholder = { Text("Información del texto") },
            label = { Text("Etiqueta") },
        )
        TextField(
            value = estadoTextField,
            onValueChange = { estadoTextField = it },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(20.dp),
            placeholder = { Text("Información del texto") },
            label = { Text("Etiqueta") },
        )*/

}

@Composable
fun NoAFilterTextField() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        label = { Text("No 'a' allowed") },
        visualTransformation = object : VisualTransformation {
            override fun filter(text: AnnotatedString): TransformedText {
                // Replace any occurrences of the letter "a" with an empty space
                val newText = text.text.replace('a', ' ')
                return TransformedText(AnnotatedString(newText), OffsetMapping.Identity)
            }
        }
    )
}

@Composable
fun MyButton() {
    Column() {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(

            )
        ) {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "emailIcon")
            Text(text = "Primer Botón", modifier = Modifier.padding(5.dp))
        }

        Button(onClick = {}, shape = RectangleShape) {
            Text(text = "Rectangle shape")
        }

        Button(onClick = {}, shape = RoundedCornerShape(20.dp)) {
            Text(text = "Round corner shape")
        }

        Button(onClick = {}, shape = CutCornerShape(10)) {
            Text(text = "Cut corner shape")
        }

        var enable by rememberSaveable { mutableStateOf(true) }

        OutlinedButton(
            onClick = { enable = false },
            enabled = enable,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Outlined Button")

        }
        TextButton(onClick = { /*TODO*/ }) {
            //Text(text = "Botón texto")
            Image(painterResource(id = R.drawable.bola), contentDescription = "Bola")
        }
        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text("Tonal")
        }
        FilledTonalIconButton(onClick = { /*TODO*/ }) {
            Text("Icon")
        }
    }
}

@Composable
fun MyStateExample() {
    var counter by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            counter += 1
        }) {
            Text(text = "Pulsar")
        }
        Text(text = "He sido pulsado $counter veces")
    }
}

@Composable
fun MyButton2() {
    var enable by rememberSaveable { mutableStateOf(true) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enable = false },
            enabled = enable,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Cyan
            ),
            border = BorderStroke(5.dp, Color.Gray)
        ) {
            Text(text = "Primer Botón")
        }
    }
}
