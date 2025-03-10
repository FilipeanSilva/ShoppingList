# **TrackShop – Android Shopping List Application**

## **About the Project**
This project was developed as part of the **Mobile Architectures** course during the **2020/2021** academic year. The main goal was to create an **Android mobile application** that allows users to manage a **personal shopping list** efficiently.

## **Features**
- **Shopping List Management**
  - Add new products to a shopping list.
  - Remove items from the list.
  - Move products from the shopping list to the cart.
  - Store a history of the last 10 purchases.
- **Data Storage**
  - Shopping lists are stored in **text files (TXT) in internal storage**.
- **Multilingual Support**
  - The application supports **Portuguese and English**.
  - The default language is **Portuguese**.
- **Screen Orientation**
  - The app can be used in both **portrait and landscape modes**.

## **Technical Stack**
- **Programming Language:** Java (Android Development)
- **Data Storage:** Internal file storage (`.txt` files)
- **UI Framework:** Android UI components (ListView, Adapters)
- **Design Patterns:** MVC (Model-View-Controller)

## **File Structure**
The project includes the following key **activities**:

| **Activity**                | **Description** |
|----------------------------|---------------|
| `MainActivity.java`        | Displays the main menu of the application. |
| `NovoProdutoActivity.java` | Allows users to create a new product. |
| `ListaComprasActivity.java` | Manages and displays the shopping list (**uses `ListaAdapter` for ListView**). |
| `CarrinhoActivity.java`    | Manages and displays the shopping cart. |
| `HistoricoActivity.java`   | Displays the **last 10 purchases**. |

Additionally, the project includes **core classes**:

| **Class**       | **Description** |
|---------------|---------------|
| `Produto.java`  | Stores product information. |
| `DadosLista.java`  | Manages the **shopping list** (vector and utility methods). |
| `DadosCarrinho.java` | Manages the **cart items**. |
| `Historico.java` | Manages the **purchase history**. |

## **How to Run**
1. **Clone the Repository**
   ```sh
   git clone https://github.com/yourusername/TrackShop.git
   cd TrackShop
   ```
2. **Open in Android Studio**
   - Import the project into **Android Studio**.
   - Ensure the necessary **SDKs and dependencies** are installed.
3. **Run the App**
   - Connect an Android device or **use an emulator**.
   - Click **Run** (`Shift + F10` in Android Studio).

## **User Interface**
The application consists of:
- A **main menu** for navigation.
- A **shopping list screen** to manage items.
- A **cart view** for finalizing purchases.
- A **history view** to track previous purchases.

## **Final Thoughts**
This project provided valuable insights into **Android development**, particularly in handling **internal storage, UI management, and shopping list functionalities**. Although better time management could have improved the project, it successfully **reinforced core Android programming concepts**.
