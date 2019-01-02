from flask import Blueprint
from flask_restful import Api
from resources.Hello import Hello

api_db = Blueprint('api', __name__)
api = Api(api_db)

# Route
api.add_resource(Hello, '/hello')