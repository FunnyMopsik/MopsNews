import requests
from bs4 import BeautifulSoup
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

# Ініціалізація з'єднання з Firebase
cred = credentials.Certificate("C:\\Users\\Администратор\\Desktop\\google-services.json")

firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://mopsnews-5da1b-default-rtdb.europe-west1.firebasedatabase.app/'
})

# URL сторінки, яку потрібно розібрати
url = "https://www.unian.ua/curiosities"

# Запит до сервера та отримання HTML-вмісту сторінки
response = requests.get(url)
html_content = response.content

# Створення об'єкту BeautifulSoup для парсингу HTML
soup = BeautifulSoup(html_content, "html.parser")

# Заданий CSS-селектор для елементів новин
css_selector = "body div:nth-of-type(2) div div:nth-of-type(2) div:nth-of-type(1) div:nth-of-type(4) div:nth-of-type(1) div div:nth-of-type(1) div a"


# Пошук елементів за вказаним CSS-селектором
element = soup.select(css_selector)

# Отримання посилання на базу даних в Firebase
firebase_ref = db.reference('news')

# Отримання поточного значення поля 'n'
current_n = firebase_ref.child('n').get()

# Перевірка на 'None' та встановлення значення 0, якщо 'n' є 'None'
#current_n = current_n or 0

# Оновлення поля 'n' з номером останньої новини


# Додавання даних до Firebase

href = element[0].get("href")
img = element[0].find("img")
alt = img.get("alt") if img else None
src = img.get("src") if img else None

news_data = firebase_ref.get()

# Перевірка наявності значення desired_handler в полі handler
if alt:
        handler_exists = firebase_ref.child('n').child('handler').get() is not None

        if handler_exists:
            print(f"Field 'handler' exists in the Firebase database for alt: {alt}")
        else:
            new_entry = {
   		        'n+(current_n + 1)': {
        	        'author': "Union.ua",
        	        'category': "Розваги",
        	        'handler': alt,
        	        'image': src,
                    'link': href
    		    }
	        }

    # Додавання нового запису до бази даних
            firebase_ref.update(new_entry)
	
            print(f"Added entry to Firebase: {new_entry}")

            print("Data has been added to Firebase.")



