import React, { useEffect, useState } from "react";
import "./assets/scss/App.scss";
import RegisterForm from "./RegisterForm";
import SearchBar from "./SearchBar";
import Emaillist from "./Emaillist";
// import data from './assets/json/data';

function App() {
  const [emails, setEmails] = useState([]);

  const getEmail = async (keyword) => {
    try {
      const res = await fetch(`/api?kw=${keyword ? keyword : ""}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });
      const data = await res.json();
      console.log(data);
      console.log("fetch");
      setEmails(data.data);
      if (!res.ok) {
        throw new Error(`${res.status} ${res.statusText}`);
      }
      if (data.result !== "success") {
        throw new Error(`${data.result} ${data.message}`);
      }
    } catch (e) {
      console.error(e);
    }
  };

  const addEmail = async (email) => {
    try {
      const response = await fetch("/api", {
        method: "post",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(email),
      });

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const json = await response.json();

      if (json.result !== "success") {
        throw new Error(`${json.result} ${json.message}`);
      }

      setEmails([json.data, ...emails]);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    getEmail();
  }, []);

  return (
    <div id={"App"}>
      <RegisterForm addEmail={addEmail} />
      <SearchBar fetchEmails={getEmail} />
      {emails === null ? null : <Emaillist emails={emails} />}
    </div>
  );
}

export { App };
