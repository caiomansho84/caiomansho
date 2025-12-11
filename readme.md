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
targetSdk	34
compileSdk	34
Principais dependências
dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-android-compiler:2.51")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Retrofit + OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
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

Retrofit para comunicação com API

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

Opção de tentar novamente

Log contendo detalhes da exceção

Exemplo de resposta esperada:
{
  "error": "Forbidden",
  "code": 403,
  "message": "Operação não autorizada"
}

📬 Como simular o push local após autorização
Método 1 — Usando ADB (recomendado)

Simular push no dispositivo/emulador:

adb shell am broadcast \
  -a com.example.APP_AUTH_SUCCESS \
  --es userId 12345 \
  --es status "AUTHORIZED"


Isso dispara o BroadcastReceiver configurado no app.

Método 2 — Via código (Debug Menu)

Se o app tiver uma DevMenu:

Menu → "Simular Push" → "AUTHORIZED"

Método 3 — FakeService interno do app

Se você incluiu um FakePushService, basta chamar:

FakePushService.sendAuthorizedPush()


Exemplo:

📄 Licença

MIT / Apache 2.0 / Proprietária (escolher)
