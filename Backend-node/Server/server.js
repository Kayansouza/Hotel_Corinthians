import express from "express";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const app = express();
app.use(express.json());

console.log("Iniciando servidor...");

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

function lerJSON(caminho) {
  try {
    return JSON.parse(fs.readFileSync(caminho, "utf-8"));
  } catch (error) {
    console.error(error);
    return null;
  }
}

app.get("/hotel", (req, res) => {
  const filePath = path.join(__dirname, "../database/seed/hotel002.json");
  const data = lerJSON(filePath);

  if (!data) return res.status(500).json({ erro: "Erro ao carregar hotel" });

  res.json(data);
});

app.get("/hospedes", (req, res) => {
  const filePath = path.join(__dirname, "../database/seed/hospe001.json");
  const data = lerJSON(filePath);

  if (!data) return res.status(500).json({ erro: "Erro ao carregar hóspedes" });

  res.json(data);
});

app.get("/reservas", (req, res) => {
  const filePath = path.join(__dirname, "../database/seed/Reservas.json");
  const data = lerJSON(filePath);

  if (!data) return res.status(500).json({ erro: "Erro ao carregar reservas" });

  res.json(data);
});

app.listen(3000, () => {
  console.log("Servidor rodando na porta 3000 🚀");
});