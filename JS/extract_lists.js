const fs = require('fs');

// Читання файлу spaces.json
fs.readFile('resources/spaces.json', 'utf8', (err, data) => {
    if (err) {
        console.error('Помилка читання файлу:', err);
        return;
    }

    try {
        // Парсинг JSON
        const spaces = JSON.parse(data);

        // Перевірка, чи є spaces масивом або об'єктом
        if (Array.isArray(spaces.folders)) {
            // Якщо folders - масив
            spaces.folders.forEach(space => {
                if (space.lists) {
                    space.lists.forEach(list => {
                        console.log('List ID:', list.id);
                    });
                }
            });

            spaces.folders.forEach(space => {
                if (space.lists) {
                    space.lists.forEach(list => {
                        if (list.name && list.name.toLowerCase().startsWith('test')) {
                            console.log('Space Name:', space.space.name);
                            console.log('Space ID:', space.space.id);
                        }
                    });
                }
            });
        } else {
            console.log('Не вдалося знайти масив folders у JSON');
        }

    } catch (parseErr) {
        console.error('Помилка парсингу JSON:', parseErr);
    }
});
