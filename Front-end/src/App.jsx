import { useState } from "react";
import "./App.css";

const guestsMock = [
  {
    id: 1,
    name: "Carlos Henrique",
    email: "carlos@email.com",
    phone: "(11) 99999-9999",
    city: "São Paulo",
  },
  {
    id: 2,
    name: "Mariana Souza",
    email: "mariana@email.com",
    phone: "(11) 98888-8888",
    city: "Santos",
  },
  {
    id: 3,
    name: "João Pedro",
    email: "joao@email.com",
    phone: "(11) 97777-7777",
    city: "Guarulhos",
  },
  {
    id: 4,
    name: "Ana Beatriz",
    email: "ana@email.com",
    phone: "(11) 96666-6666",
    city: "Campinas",
  },
  {
    id: 5,
    name: "Rafael Lima",
    email: "rafael@email.com",
    phone: "(11) 95555-5555",
    city: "Osasco",
  },
  {
    id: 6,
    name: "Fernanda Alves",
    email: "fernanda@email.com",
    phone: "(11) 94444-4444",
    city: "São Bernardo",
  },
];

const roomsMock = [
  {
    id: 1,
    number: 101,
    type: "Simples",
    capacity: "2 pessoas",
    price: 180,
    status: "Disponível",
    statusClass: "available",
  },
  {
    id: 2,
    number: 204,
    type: "Luxo",
    capacity: "3 pessoas",
    price: 320,
    status: "Ocupado",
    statusClass: "occupied",
  },
  {
    id: 3,
    number: 301,
    type: "Suíte Premium",
    capacity: "4 pessoas",
    price: 520,
    status: "Disponível",
    statusClass: "available",
  },
  {
    id: 4,
    number: 118,
    type: "Simples",
    capacity: "2 pessoas",
    price: 180,
    status: "Manutenção",
    statusClass: "maintenance",
  },
];

function App() {
  const [activeSection, setActiveSection] = useState("dashboard");

  const [guestName, setGuestName] = useState("");
  const [roomType, setRoomType] = useState("");
  const [days, setDays] = useState("");
  const [season, setSeason] = useState("normal");
  const [reservation, setReservation] = useState(null);

  const availableRooms = roomsMock.filter(
    (room) => room.status === "Disponível"
  ).length;

  const occupiedRooms = roomsMock.filter(
    (room) => room.status === "Ocupado"
  ).length;

  const revenueEstimated = 8450;

  const roomPrices = {
    simples: {
      name: "Quarto Simples",
      price: 180,
    },
    luxo: {
      name: "Quarto Luxo",
      price: 320,
    },
    suite: {
      name: "Suíte Premium",
      price: 520,
    },
  };

  const seasonRates = {
    normal: {
      label: "Período normal",
      rate: 0,
    },
    weekend: {
      label: "Fim de semana",
      rate: 0.15,
    },
    holiday: {
      label: "Feriado",
      rate: 0.25,
    },
  };

  function formatCurrency(value) {
    return value.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
    });
  }

  function handleReservationSubmit(event) {
    event.preventDefault();

    const selectedRoom = roomPrices[roomType];
    const selectedSeason = seasonRates[season];
    const selectedDays = Number(days);

    if (!guestName || !selectedRoom || selectedDays <= 0) {
      alert("Preencha todos os campos corretamente.");
      return;
    }

    const baseValue = selectedRoom.price * selectedDays;
    const extraValue = baseValue * selectedSeason.rate;
    const total = baseValue + extraValue;

    setReservation({
      guestName,
      roomName: selectedRoom.name,
      days: selectedDays,
      season: selectedSeason.label,
      baseValue,
      extraValue,
      total,
    });
  }

  return (
    <div className="app">
      <aside className="sidebar">
        <div className="brand">
          <div className="brand-icon">HC</div>
          <div>
            <h2>Hotel Corinthians</h2>
            <p>Sistema Web</p>
          </div>
        </div>

        <nav className="menu">
          <button
            className={activeSection === "dashboard" ? "active" : ""}
            onClick={() => setActiveSection("dashboard")}
          >
            Dashboard
          </button>

          <button
            className={activeSection === "guests" ? "active" : ""}
            onClick={() => setActiveSection("guests")}
          >
            Hóspedes
          </button>

          <button
            className={activeSection === "rooms" ? "active" : ""}
            onClick={() => setActiveSection("rooms")}
          >
            Quartos
          </button>

          <button
            className={activeSection === "reservation" ? "active" : ""}
            onClick={() => setActiveSection("reservation")}
          >
            Reservas
          </button>
        </nav>

        <div className="sidebar-footer">
          <p>Projeto em React + Vite</p>
          <span>Versão para apresentação</span>
        </div>
      </aside>

      <main className="content">
        <section className="hero">
          <div>
            <span className="badge">Módulo funcional</span>
            <h1>Gestão inteligente para hotelaria</h1>
            <p>
              Uma versão web inicial para controlar hóspedes, quartos e simular
              reservas com regra de precificação automática.
            </p>
          </div>

          <div className="hero-card">
            <strong>{formatCurrency(revenueEstimated)}</strong>
            <span>Receita estimada do mês</span>
          </div>
        </section>

        {activeSection === "dashboard" && (
          <section className="section">
            <div className="stats-grid">
              <article className="stat-card">
                <p>Total de quartos</p>
                <strong>{roomsMock.length}</strong>
                <span>Unidades cadastradas</span>
              </article>

              <article className="stat-card">
                <p>Disponíveis</p>
                <strong>{availableRooms}</strong>
                <span>Prontos para reserva</span>
              </article>

              <article className="stat-card">
                <p>Ocupados</p>
                <strong>{occupiedRooms}</strong>
                <span>Reservas em andamento</span>
              </article>

              <article className="stat-card">
                <p>Hóspedes</p>
                <strong>{guestsMock.length}</strong>
                <span>Clientes cadastrados</span>
              </article>
            </div>

            <div className="dashboard-grid">
              <article className="panel">
                <div className="panel-header">
                  <h2>Resumo operacional</h2>
                  <span>Hoje</span>
                </div>

                <div className="timeline">
                  <div>
                    <strong>08:30</strong>
                    <p>Check-in previsto para Mariana Souza</p>
                  </div>

                  <div>
                    <strong>11:00</strong>
                    <p>Quarto 118 enviado para manutenção</p>
                  </div>

                  <div>
                    <strong>14:00</strong>
                    <p>Suíte Premium disponível para reserva</p>
                  </div>
                </div>
              </article>

              <article className="panel">
                <div className="panel-header">
                  <h2>Próximas melhorias</h2>
                  <span>Roadmap</span>
                </div>

                <ul className="roadmap">
                  <li>Integração completa com API</li>
                  <li>Cadastro de reservas no banco de dados</li>
                  <li>Autenticação de usuários</li>
                  <li>Validações e testes automatizados</li>
                </ul>
              </article>
            </div>
          </section>
        )}

        {activeSection === "guests" && (
          <section className="section">
            <div className="section-header">
              <div>
                <span className="badge">Dados mockados</span>
                <h2>Hóspedes cadastrados</h2>
                <p>
                  Tela simulando o retorno de uma API para validar layout,
                  experiência e fluxo de visualização dos clientes.
                </p>
              </div>
            </div>

            <div className="guests-grid">
              {guestsMock.map((guest) => (
                <article className="guest-card" key={guest.id}>
                  <div className="guest-avatar">
                    {guest.name.charAt(0).toUpperCase()}
                  </div>

                  <div>
                    <h3>{guest.name}</h3>
                    <p>{guest.email}</p>
                    <span>{guest.phone}</span>
                    <small>{guest.city}</small>
                  </div>
                </article>
              ))}
            </div>
          </section>
        )}

        {activeSection === "rooms" && (
          <section className="section">
            <div className="section-header">
              <div>
                <span className="badge">Controle operacional</span>
                <h2>Quartos do hotel</h2>
                <p>
                  Visualização rápida dos quartos, status, capacidade e valor da
                  diária.
                </p>
              </div>
            </div>

            <div className="rooms-grid">
              {roomsMock.map((room) => (
                <article className="room-card" key={room.id}>
                  <div className="room-top">
                    <div>
                      <h3>Quarto {room.number}</h3>
                      <p>
                        {room.type} • {room.capacity}
                      </p>
                    </div>

                    <span className={room.statusClass}>{room.status}</span>
                  </div>

                  <div className="room-price">
                    <p>Diária</p>
                    <strong>{formatCurrency(room.price)}</strong>
                  </div>
                </article>
              ))}
            </div>
          </section>
        )}

        {activeSection === "reservation" && (
          <section className="section reservation-layout">
            <article className="panel">
              <div className="panel-header">
                <div>
                  <h2>Nova reserva</h2>
                  <p>Simule uma reserva com cálculo automático.</p>
                </div>
              </div>

              <form onSubmit={handleReservationSubmit} className="form">
                <label>
                  Nome do hóspede
                  <input
                    type="text"
                    placeholder="Ex: Carlos Henrique"
                    value={guestName}
                    onChange={(event) => setGuestName(event.target.value)}
                  />
                </label>

                <label>
                  Tipo de quarto
                  <select
                    value={roomType}
                    onChange={(event) => setRoomType(event.target.value)}
                  >
                    <option value="">Selecione</option>
                    <option value="simples">Simples - R$ 180</option>
                    <option value="luxo">Luxo - R$ 320</option>
                    <option value="suite">Suíte Premium - R$ 520</option>
                  </select>
                </label>

                <label>
                  Quantidade de diárias
                  <input
                    type="number"
                    min="1"
                    placeholder="Ex: 3"
                    value={days}
                    onChange={(event) => setDays(event.target.value)}
                  />
                </label>

                <label>
                  Período
                  <select
                    value={season}
                    onChange={(event) => setSeason(event.target.value)}
                  >
                    <option value="normal">Normal</option>
                    <option value="weekend">Fim de semana +15%</option>
                    <option value="holiday">Feriado +25%</option>
                  </select>
                </label>

                <button type="submit">Calcular reserva</button>
              </form>
            </article>

            <article className="panel result-panel">
              <div className="panel-header">
                <div>
                  <h2>Resumo da reserva</h2>
                  <p>Resultado da regra de precificação.</p>
                </div>
              </div>

              <div className="result-box">
                <p>Hóspede</p>
                <strong>{reservation ? reservation.guestName : "---"}</strong>
              </div>

              <div className="result-box">
                <p>Quarto</p>
                <strong>{reservation ? reservation.roomName : "---"}</strong>
              </div>

              <div className="result-box">
                <p>Período</p>
                <strong>{reservation ? reservation.season : "---"}</strong>
              </div>

              <div className="result-box">
                <p>Diárias</p>
                <strong>
                  {reservation ? `${reservation.days} diária(s)` : "---"}
                </strong>
              </div>

              <div className="result-total">
                <p>Valor final</p>
                <strong>
                  {reservation ? formatCurrency(reservation.total) : "R$ 0,00"}
                </strong>
              </div>

              <div className="status-message">
                <span></span>
                {reservation
                  ? "Reserva calculada com sucesso"
                  : "Aguardando preenchimento"}
              </div>
            </article>
          </section>
        )}
      </main>
    </div>
  );
}

export default App;