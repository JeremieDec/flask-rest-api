<div align="center">

  <img src="https://cdn2.iconfinder.com/data/icons/picons-basic-2/57/basic2-004_comment_chat-512.png" width="185px"/>

</div>

# Flask REST API 


### Requeriments
- Python 3.x
- PostgreSQL database
- virtualenv

### Install requeriments
```pip install -r requirements.txt```

### Run migrations
- ```python migrate.py db init```
- ```python migrate.py db migrate```
- ```python migrate.py db upgrade```

### Run API
```python run.py```

## API ENDPOINTS

- **GET** - ```/api/Category - Retrieve all categories```
- **POST** - ```/api/Category - Add a new category```
- **PUT** - ```/api/Category - Update a category```
- **DELETE** - ```/api/Category - Delete a category```
- **GET** - ```/api/Comment - Retrieve all the stored comments```
- **POST** - ```/api/Comment - Add new comment```


