import joblib
from fastapi import FastAPI, UploadFile, File
import numpy as np
from skimage.io import imread
from skimage.transform import resize

app = FastAPI()

# Load updated model components
model = joblib.load("brain_tumor_model.pkl")
scaler = joblib.load("scaler.pkl")
label_encoder = joblib.load("label_encoder.pkl")
print("✅ Model, Scaler, and LabelEncoder loaded successfully")

@app.post("/predict")
async def predict(file: UploadFile = File(...)):
    try:
        contents = await file.read()
        img = imread(np.frombuffer(contents, np.uint8), as_gray=True)
        img_resized = resize(img, (128, 128))
        img_flat = img_resized.flatten().reshape(1, -1)

        img_scaled = scaler.transform(img_flat)
        prediction = model.predict(img_scaled)
        predicted_label = label_encoder.inverse_transform(prediction)

        return {"prediction": predicted_label[0]}
    except Exception as e:
        return {"error": f"Processing failed: {str(e)}"}
