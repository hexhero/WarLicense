import base64

def encode(str):
    return base64.b64encode(str.encode("utf-8"))

def decode(str):
    return base64.b64decode(str.decode('utf-8'))