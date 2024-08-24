from flask import Flask, request

app = Flask(__name__)

# Home route
@app.route('/')
def home():
    return '''
        <h1>Hello AWS!</h1>
        <form action="/welcome" method="post">
            <label for="name">What is your name?</label>
            <input type="text" id="name" name="name">
            <input type="submit" value="Submit">
        </form>
    '''

# Welcome route to handle the form submission
@app.route('/welcome', methods=['POST'])
def welcome():
    name = request.form.get('name')
    return f'<h1>Welcome to AWS journey, {name}!</h1>'

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
