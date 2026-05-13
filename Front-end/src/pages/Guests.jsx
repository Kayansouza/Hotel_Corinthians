import { useEffect, useState } from "react";
import { api } from "../services/api";

function Guests() {
  const [guests, setGuests] = useState([]);

  useEffect(() => {
    async function loadGuests() {
      try {
        const response = await api.get("/guests");
        setGuests(response.data);
      } catch (error) {
        console.error("Erro ao buscar hóspedes:", error);
      }
    }

    loadGuests();
  }, []);

  return (
    <main>
      <h1>Hóspedes</h1>

      {guests.length === 0 ? (
        <p>Nenhum hóspede cadastrado.</p>
      ) : (
        <ul>
          {guests.map((guest) => (
            <li key={guest.id}>
              {guest.name} - {guest.email}
            </li>
          ))}
        </ul>
      )}
    </main>
  );
}

export default Guests;