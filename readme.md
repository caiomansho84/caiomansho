🚀 Como executar o app
▶️ 1. Executar no Emulador (Android Studio)

Abra o projeto no Android Studio

Vá em Device Manager

Crie um dispositivo virtual (Pixel 6, por exemplo)

Certifique-se de que o emulador possui:

Google Play Services

API Level compatível (ex. 33 ou 34)

Clique em Run ▶️ para instalar e iniciar o app

📱 2. Executar em Dispositivo Físico

Ative Modo Desenvolvedor no Android
Configurações → Sobre o telefone → Número da versão (pressionar 7x)

Ative Depuração USB

Conecte o dispositivo ao PC (USB ou Wi-Fi)

Aceite a permissão "Allow USB debugging"

No Android Studio, selecione seu dispositivo físico

Clique em Run ▶️

🧪 Como rodar os testes
1. Testes Unitários (JUnit)
./gradlew test

2. Testes Instrumentados (Android)
./gradlew connectedAndroidTest


Ou pelo Android Studio:
Run → Run Tests ou botão verde ao lado das classes de teste.

📦 Dependências e Versões do SDK
SDK
Config	Versão
minSdk	24
targetSdk	36
compileSdk	36
Principais dependências
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil3.compose)
    implementation(libs.coil3.network.okhttp)

    implementation(libs.androidx.compose.material.iconsExtended)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}


(Ajuste para refletir seu projeto real.)

🏗️ Decisões Arquiteturais e Motivações
Arquitetura

MVVM (Model-View-ViewModel)

Unidirectional Data Flow (UI → ViewModel → UseCase → Repository → DataSource)

StateFlow para estados reativos da UI

Jetpack Compose para UI declarativa

Hilt para Injeção de Dependência

Repository Pattern para separação entre domínio e dados

Coroutines/Flows para assíncrono

Motivações

Escalabilidade: Camadas bem definidas facilitam testes e manutenção

Reatividade: Compose + Flow simplificam a renderização e evitam inconsistências

Testabilidade: UseCases e Repository isoláveis

Modularidade: Facilita adicionar novas features sem impacto cruzado

💸 Cenário de falha (R$403) — Como testar

O fluxo de "Falha de Autorização" retorna erro R$403 simulando falha do lado do banco/servidor.

🔧 Como testar:

Vá até a tela onde ocorre a operação financeira

Insira o valor R$ 403,00

A API/Mock retornará automaticamente um erro

O app deve exibir:

Tela de erro amigável

📄 Licença

MIT / Apache 2.0 / Proprietária (escolher)
