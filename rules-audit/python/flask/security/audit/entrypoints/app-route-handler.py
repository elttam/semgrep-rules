from flask import Flask

app = Flask(__name__)

# ruleid: flask-app-route-handler
@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"

# ruleid: flask-app-route-handler
@app.route('/path/<path:subpath>')
def show_subpath(subpath):
    # show the subpath after /path/
    return f'Subpath {escape(subpath)}'

# ruleid: flask-app-route-handler
@app.get('/login')
def login_get():
    return show_the_login_form()

# ruleid: flask-app-route-handler
@app.post('/login')
def login_post():
    return do_the_login()
