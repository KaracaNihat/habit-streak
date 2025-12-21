import api from "./axiosConfig";

export const registerUser = (data: { email: string; password: string }) =>
    api.post("/api/auth/register", data);

export const loginUser = async (data: { email: string; password: string }) => {
    const res = await api.post("/api/auth/login", data);
    localStorage.setItem("jwt", res.data.token); // save token
    return res.data;
};
