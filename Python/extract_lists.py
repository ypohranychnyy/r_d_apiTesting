import json

# Читання файлу spaces.json
with open('resources/spaces.json', 'r', encoding='utf-8') as file:
    data = json.load(file)

# Знаходження всіх об'єктів "lists" і виведення їх ID
for folder in data['folders']:
    if 'lists' in folder:
        for lst in folder['lists']:
            print(f"List ID: {lst['id']}")

# Виведення space.name та space.id для lists, де ім'я починається зі слова "test"
for folder in data['folders']:
    if 'lists' in folder:
        for lst in folder['lists']:
            if 'name' in lst and lst['name'].lower().startswith('test'):
                print(f"Space Name: {folder['space']['name']}")
                print(f"Space ID: {folder['space']['id']}")
