import React, { useState } from "react";
import Titlebar from "../components/Titlebar";

export default function UsersList() {
  const [mock_users, setMockUsers] = useState([
    {
      idUser: 1,
      email: "user1@email.com",
    },
    {
      idUser: 2,
      email: "user2@email.com",
    },
  ]);

  function removeUser(userToRemove: { idUser: number; email: string }) {
    setMockUsers((prevUsers) =>
      prevUsers.filter((user) => user !== userToRemove)
    );
  }

  return (
    <>
      <Titlebar />
      <div className="container text-center">
        <h1>Korisnici</h1>
        <ul className="list-group">
          {mock_users.map((item) => (
            <li className="list-group-item light" key={item.idUser}>
              <h3 className="float-start">{item.email}</h3>
              <div
                className="btn btn-danger float-end"
                title="Ukloni korisnika"
                onClick={() => removeUser(item)}
              >
                Ukloni
              </div>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
}
