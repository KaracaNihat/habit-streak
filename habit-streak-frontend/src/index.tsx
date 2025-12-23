import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap-grid.min.css';
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import PrivateRoute from "./components/PrivateRoute";
import Register from "./components/Register";
import "./index.css";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <React.StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route
                    path="/dashboard"
                    element={
                        <PrivateRoute>
                            <Dashboard/>
                        </PrivateRoute>
                    }
                />
            </Routes>
        </BrowserRouter>
    </React.StrictMode>
);
