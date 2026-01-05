from flask import Flask, request, jsonify
from flask_cors import CORS
import os

app = Flask(__name__)
CORS(app)  # Allow all origins (for dev/testing)

# Optional: use config from environment (simulating ConfigMap / Secret)
APP_NAME = os.getenv("APP_NAME", "Flask Calculator")
APP_SECRET = os.getenv("APP_SECRET", "dev-secret")  # example secret usage

@app.route("/")
def home():
    return jsonify({"message": f"Welcome to {APP_NAME}!"})

@app.route("/calculate", methods=["POST"])
def calculate():
    data = request.json
    operation = data.get("operation")
    a = data.get("a")
    b = data.get("b")

    try:
        a = float(a)
        b = float(b)
    except (ValueError, TypeError):
        return jsonify({"error": "Invalid numbers"}), 400

    if operation == "add":
        result = a + b
    elif operation == "subtract":
        result = a - b
    elif operation == "multiply":
        result = a * b
    elif operation == "divide":
        if b == 0:
            return jsonify({"error": "Division by zero"}), 400
        result = a / b
    else:
        return jsonify({"error": "Invalid operation"}), 400

    return jsonify({"result": result})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
