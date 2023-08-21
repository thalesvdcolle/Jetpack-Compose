package com.github.thalesvdcolle.jetpackcomposenooesbasicas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import com.github.thalesvdcolle.jetpackcomposenooesbasicas.ui.theme.JetpackComposeNoçoesBasicasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeNoçoesBasicasTheme {
                MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

@Composable
private fun MyApp(modifier: Modifier = Modifier){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
    Greetings()
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List <ItemCompra> = listaCompra) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name.nome, desc = name.desc, quant = name.quant)

        }
    }
}

@Composable
fun Greeting(name: String, desc: String, quant: Int) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState( if (expanded) 48.dp else 0.dp, animationSpec = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    ))
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
       Row(modifier = Modifier .padding(24.dp)) {
           Column(modifier = Modifier
               .weight(1f)
               .padding(bottom = extraPadding.coerceAtLeast(0.dp))
           ) {
               Text(text = name)
               if (expanded){
               Text(text = desc)
               } else " "
           }
           Button(onClick = { expanded = !expanded}) {
               Text(if (expanded) "Mostre Menos" else "Mostre Mais")
           }
       }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem Vindo a lista de Compras")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackComposeNoçoesBasicasTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackComposeNoçoesBasicasTheme {
        Greetings()
    }
}

@Preview
@Composable
fun MyAppPreview() {
    JetpackComposeNoçoesBasicasTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

data class ItemCompra(
    val nome :String,
    val quant : Int,
    val desc : String
)

val listaCompra = listOf(

    ItemCompra(
        nome = "Maçã",
        quant = 3,
        desc = "fruta vermelha de sabor adocicado e agradavel ao paladar."
    ),
    ItemCompra(
        nome = "Banana",
        quant = 2,
        desc = "fruta amarela de sabor doce e otima batida com leite."
),
    ItemCompra(
        nome = "Uva",
        quant = 1 ,
        desc = "fruta formada em caixos e muito doce de forma a ser otima para vinhos."
    ),
    ItemCompra(
        nome = "Telefone celular",
        quant = 2,
        desc = "sistema que une software e hardware de forma convencional e unica."
    ),
    ItemCompra(
        nome = "Copos",
        quant = 6,
        desc = "formado por vidro aplica-se para guardar agua de forma a toma-la."
    )
)