import React, { useEffect, useState } from "react";
import ConferenceNavbar from "../components/ConferenceNavbar";
import Loading from "../components/Loading";

/* private Integer idKorisnik;
    private String email;
    private String ime;
    private String prezime;
 */
interface GetUser {
  idKorisnik: number;
  email: string;
  ime: string;
  prezime: string;
}

interface PostUser {
  email: string;
  ime: string;
  prezime: string;
  admin: boolean;
  visitor: boolean;
  idKonferencije: number;
  lozinka: string;
}

interface DeleteUser {
  email: string;
}

export default function UsersList() {
  const [isLoading, setIsLoading] = useState(true);
  const [users, setUsers] = useState<GetUser[]>([]);

  async function getUsers() {
    const conferenceId = localStorage.getItem("conferenceId");
    const token = localStorage.getItem("jwtToken");
    const response = await fetch(`/api/korisnici`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    const data = await response.json();
    console.log(data);
    return data;
  }

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
      setUsers((prev) =>
        prev.filter((user) => user.email !== userToRemove.email)
      );
    }
  }

  useEffect(() => {
    setIsLoading(true);
    getUsers().then((data) => {
      setUsers(data);
      setIsLoading(false);
    });
  }, []);

  return (
    <>
      <ConferenceNavbar />
      {isLoading ? (
        <Loading />
      ) : (
        <div className="container text-center">
          <h1>Korisnici</h1>
          <ul className="list-group">
            {users.map((user) => (
              <li className="list-group-item light" key={user.idKorisnik}>
                <h3 className="float-start">{user.email}</h3>
                <div
                  className="btn btn-danger float-end"
                  title="Ukloni korisnika"
                  onClick={() => {
                    removeUser(user);
                  }}
                >
                  Ukloni
                </div>
              </li>
            ))}
          </ul>
        </div>
      )}
    </>
  );
}
