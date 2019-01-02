from flask_restful import Resource


class Hello:
    def get():
        return {"message": "Hello world!"}

    def post(self):
        return {"message": "Hello world!"}
