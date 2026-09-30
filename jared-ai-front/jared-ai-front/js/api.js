import { CONFIG } from "./config.js";

const RESUMO_EXEMPLO = [
  "Resumo de exemplo (simulado).",
  "A fotossíntese é o jeito que as plantas têm de fazer o próprio alimento. Elas usam a luz do sol, a água e o ar para isso.",
  "Pense numa cozinha: as folhas são a cozinha, o sol é o fogão e a água e o ar são os ingredientes. No final, a planta \"cozinha\" o seu alimento e ainda solta oxigênio, que a gente respira.",
].join("\n\n");

const esperar = (ms) => new Promise((resolver) => setTimeout(resolver, ms));


export async function resumirArquivo(arquivo) {
  if (CONFIG.USAR_MOCK) {
    await esperar(1500);
    return RESUMO_EXEMPLO;
  }
  return enviarParaApi(arquivo);
}

async function enviarParaApi(arquivo) {
  const dados = new FormData();
  dados.append(CONFIG.CAMPO_ARQUIVO, arquivo);

  let resposta;
  try {
    resposta = await fetch(CONFIG.API_BASE_URL + CONFIG.ENDPOINT_RESUMIR, {
      method: "POST",
      body: dados,
    });
  } catch {
    throw new Error("Não foi possível falar com o servidor. Verifique se ele está ligado.");
  }

  if (!resposta.ok) {
    throw new Error(`O servidor respondeu com erro (${resposta.status}). Tente novamente.`);
  }

  const json = await resposta.json();
  return json[CONFIG.CAMPO_RESPOSTA] ?? "";
}
