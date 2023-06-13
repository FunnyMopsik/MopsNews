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

humor = " "
politic = " "
games = " "
sport = " "


def checkHumor():
    # URL сторінки, яку потрібно розібрати
    url = "https://www.unian.ua/gumor"

    # Запит до сервера та отримання HTML-вмісту сторінки
    response = requests.get(url)
    html_content = response.content

    # Створення об'єкту BeautifulSoup для парсингу HTML
    soup = BeautifulSoup(html_content, "html.parser")

    # Заданий CSS-селектор для елементів новин
    css_selector = "body div:nth-of-type(2) div div:nth-of-type(2) div:nth-of-type(1) div:nth-of-type(4) div:nth-of-type(1) div div:nth-of-type(1) div a"

    # Пошук елемента за вказаним CSS-селектором
    element = soup.select_one(css_selector)

    # Отримання посилання на базу даних в Firebase
    firebase_ref = db.reference('news')

    # Отримання поточного значення поля 'n'
    news_data = firebase_ref.child('news').get()
    current_n = len(news_data)

    # Перевірка наявності поля 'n' і встановлення значення за замовчуванням, якщо поле не існує

    # Збільшення значення 'n' на 1





    # Додавання даних до Firebase
    if element:
        href = element.get("href")
        img = element.find("img")
        alt = img.get("alt") if img else None
        src = img.get("src") if img else None

        # Перевірка наявності значення 'alt' для зображення
        if alt != humor:
            handler_exists = firebase_ref.child('n').child('handler').get() is not None

            if handler_exists:
                print(f"Field 'handler' exists in the Firebase database for alt: {alt}")
            else:
                new_entry = {
                    'n'+ str(current_n): {
                        'author': "Union.ua",
                        'category': "Гумор",
                        'header': alt,
                        'image': src,
                        'link': href
                    }
                }

                # Додавання нового запису до бази даних
                firebase_ref.update(new_entry)

                print(f"Added entry to Firebase: {new_entry}")
                print("Data has been added to Firebase.")
    humor = alt

def checkPolitic():
    # URL сторінки, яку потрібно розібрати
    url = "https://www.unian.ua/politics"

    # Запит до сервера та отримання HTML-вмісту сторінки
    response = requests.get(url)
    html_content = response.content

    # Створення об'єкту BeautifulSoup для парсингу HTML
    soup = BeautifulSoup(html_content, "html.parser")

    # Заданий CSS-селектор для елементів новин
    css_selector = "body div:nth-of-type(2) div div:nth-of-type(2) div:nth-of-type(1) div:nth-of-type(4) div:nth-of-type(1) div div:nth-of-type(1) div a"

    # Пошук елемента за вказаним CSS-селектором
    element = soup.select_one(css_selector)

    # Отримання посилання на базу даних в Firebase
    firebase_ref = db.reference('news')

    # Отримання поточного значення поля 'n'
    news_data = firebase_ref.child('news').get()
    current_n = len(news_data)

    # Перевірка наявності поля 'n' і встановлення значення за замовчуванням, якщо поле не існує

    # Збільшення значення 'n' на 1





    # Додавання даних до Firebase
    if element:
        href = element.get("href")
        img = element.find("img")
        alt = img.get("alt") if img else None
        src = img.get("src") if img else None

        # Перевірка наявності значення 'alt' для зображення
        if alt != politic:
            handler_exists = firebase_ref.child('n').child('handler').get() is not None

            if handler_exists:
                print(f"Field 'handler' exists in the Firebase database for alt: {alt}")
            else:
                new_entry = {
                    'n'+ str(current_n): {
                        'author': "Union.ua",
                        'category': "Політика",
                        'header': alt,
                        'image': src,
                        'link': href
                    }
                }

                # Додавання нового запису до бази даних
                firebase_ref.update(new_entry)

                print(f"Added entry to Firebase: {new_entry}")
                print("Data has been added to Firebase.")
    politic = alt

def checkGames():
    # URL сторінки, яку потрібно розібрати
    url = "https://www.unian.ua/games"

    # Запит до сервера та отримання HTML-вмісту сторінки
    response = requests.get(url)
    html_content = response.content

    # Створення об'єкту BeautifulSoup для парсингу HTML
    soup = BeautifulSoup(html_content, "html.parser")

    # Заданий CSS-селектор для елементів новин
    css_selector = "body div:nth-of-type(2) div div:nth-of-type(2) div:nth-of-type(1) div:nth-of-type(4) div:nth-of-type(1) div div:nth-of-type(1) div a"

    # Пошук елемента за вказаним CSS-селектором
    element = soup.select_one(css_selector)

    # Отримання посилання на базу даних в Firebase
    firebase_ref = db.reference('news')

    # Отримання поточного значення поля 'n'
    news_data = firebase_ref.child('news').get()
    current_n = len(news_data)

    # Перевірка наявності поля 'n' і встановлення значення за замовчуванням, якщо поле не існує

    # Збільшення значення 'n' на 1





    # Додавання даних до Firebase
    if element:
        href = element.get("href")
        img = element.find("img")
        alt = img.get("alt") if img else None
        src = img.get("src") if img else None

        # Перевірка наявності значення 'alt' для зображення
        if alt != games:
            handler_exists = firebase_ref.child('n').child('handler').get() is not None

            if handler_exists:
                print(f"Field 'handler' exists in the Firebase database for alt: {alt}")
            else:
                new_entry = {
                    'n'+ str(current_n): {
                        'author': "Union.ua",
                        'category': "Ігри",
                        'header': alt,
                        'image': src,
                        'link': href
                    }
                }

                # Додавання нового запису до бази даних
                firebase_ref.update(new_entry)

                print(f"Added entry to Firebase: {new_entry}")
                print("Data has been added to Firebase.")
    games = alt


def checkHumor():
    # URL сторінки, яку потрібно розібрати
    url = "https://www.unian.ua/gumor"

    # Запит до сервера та отримання HTML-вмісту сторінки
    response = requests.get(url)
    html_content = response.content

    # Створення об'єкту BeautifulSoup для парсингу HTML
    soup = BeautifulSoup(html_content, "html.parser")

    # Заданий CSS-селектор для елементів новин
    css_selector = "body div:nth-of-type(2) div div:nth-of-type(2) div:nth-of-type(1) div:nth-of-type(4) div:nth-of-type(1) div div:nth-of-type(1) div a"

    # Пошук елемента за вказаним CSS-селектором
    element = soup.select_one(css_selector)

    # Отримання посилання на базу даних в Firebase
    firebase_ref = db.reference('news')

    # Отримання поточного значення поля 'n'
    news_data = firebase_ref.child('news').get()
    current_n = len(news_data)

    # Перевірка наявності поля 'n' і встановлення значення за замовчуванням, якщо поле не існує

    # Збільшення значення 'n' на 1





    # Додавання даних до Firebase
    if element:
        href = element.get("href")
        img = element.find("img")
        alt = img.get("alt") if img else None
        src = img.get("src") if img else None

        # Перевірка наявності значення 'alt' для зображення
        if alt != humor:
            handler_exists = firebase_ref.child('n').child('handler').get() is not None

            if handler_exists:
                print(f"Field 'handler' exists in the Firebase database for alt: {alt}")
            else:
                new_entry = {
                    'n'+ str(current_n): {
                        'author': "Union.ua",
                        'category': "Гумор",
                        'header': alt,
                        'image': src,
                        'link': href
                    }
                }

                # Додавання нового запису до бази даних
                firebase_ref.update(new_entry)

                print(f"Added entry to Firebase: {new_entry}")
                print("Data has been added to Firebase.")
    humor = alt

def checkSport():
    # URL сторінки, яку потрібно розібрати
    url = "https://www.unian.ua/sport"

    # Запит до сервера та отримання HTML-вмісту сторінки
    response = requests.get(url)
    html_content = response.content

    # Створення об'єкту BeautifulSoup для парсингу HTML
    soup = BeautifulSoup(html_content, "html.parser")

    # Заданий CSS-селектор для елементів новин
    css_selector = "body div:nth-of-type(2) div div:nth-of-type(2) div:nth-of-type(1) div:nth-of-type(4) div:nth-of-type(1) div div:nth-of-type(1) div a"

    # Пошук елемента за вказаним CSS-селектором
    element = soup.select_one(css_selector)

    # Отримання посилання на базу даних в Firebase
    firebase_ref = db.reference('news')

    # Отримання поточного значення поля 'n'
    news_data = firebase_ref.child('news').get()
    current_n = len(news_data)

    # Перевірка наявності поля 'n' і встановлення значення за замовчуванням, якщо поле не існує

    # Збільшення значення 'n' на 1





    # Додавання даних до Firebase
    if element:
        href = element.get("href")
        img = element.find("img")
        alt = img.get("alt") if img else None
        src = img.get("src") if img else None

        # Перевірка наявності значення 'alt' для зображення
        if alt != sport:
            handler_exists = firebase_ref.child('n').child('handler').get() is not None

            if handler_exists:
                print(f"Field 'handler' exists in the Firebase database for alt: {alt}")
            else:
                new_entry = {
                    'n'+ str(current_n): {
                        'author': "Union.ua",
                        'category': "Спорт",
                        'header': alt,
                        'image': src,
                        'link': href
                    }
                }

                # Додавання нового запису до бази даних
                firebase_ref.update(new_entry)

                print(f"Added entry to Firebase: {new_entry}")
                print("Data has been added to Firebase.")
    sport = alt



def main():
    while True:
        checkHumor()
        checkPolitic()
        checkGames()
        checkSport()



main()



