import React, { useEffect, useState } from "react";
import ConferenceNavbar from "../components/ConferenceNavbar";

/* private Integer idKorisnik;
    private String email;
    private String ime;
    private String prezime;
 */
interface GetUser {
  idKorisnik: number,
  email: string,
  ime: string,
  prezime: string
}

interface PostUser {
  email: string,
  ime: string,
  prezime: string,
  admin: boolean,
  visitor: boolean,
  idKonferencije: number,
  lozinka: string
}

interface DeleteUser {
  email:string
}

async function getUsers() {
  const conferenceId = localStorage.getItem("conferenceId");
  const token = localStorage.getItem("jwtToken");
  const response = await fetch(
    `/api/korisnici`,
    {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
  const data = await response.json();
  console.log(data);
  return data;
}

async function postUser(user: PostUser) {
  console.log("User to send", user);
  const token = localStorage.getItem("jwtToken");
  const response = await fetch(`/api/fotografije`, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });
  const data = await response.json();
  console.log(data);
}

export default function UsersList() {
  /* const [mock_users, setMockUsers] = useState([
    {
      idUser: 1,
      email: "user1@email.com",
    },
    {
      idUser: 2,
      email: "user2@email.com",
    },
  ]); */

  const [users, setUsers] = useState<GetUser[]>([]);

  useEffect(() => {
    getUsers().then((data) => setUsers(data));
  }, []);

  async function removeUser(userToRemove: DeleteUser) {
    const token = localStorage.getItem("jwtToken");
      const response = await fetch(`/api/korisnici/${userToRemove.email}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      if (response.ok) {
        // If the deletion was successful, update the state to trigger a re-render
        setUsers((prev) => prev.filter((user) => user.email !== userToRemove.email));
      }
  }

  return (
    <>
      <ConferenceNavbar />
      <div className="container text-center">
        <h1>Korisnici</h1>
        <ul className="list-group">
          {users.map((user) => (
            <li className="list-group-item light" key={user.idKorisnik}>
              <h3 className="float-start">{user.email}</h3>
              <div
                className="btn btn-danger float-end"
                title="Ukloni korisnika"
                onClick={() => {removeUser(user)}}
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
