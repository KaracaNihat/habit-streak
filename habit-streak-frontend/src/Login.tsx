import React, {useState} from "react";
import {loginUser} from "./authApi";
import {Box, Button, Container, Stack, TextField} from "@mui/material";
import SendIcon from '@mui/icons-material/Send';

const Login: React.FC = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const res = await loginUser({email, password});
            if (res.token) {
                localStorage.setItem("jwt", res.token);
                alert("Logged in!");
            } else {
                alert("Login failed: no token received");
            }
        } catch (err) {
            console.error(err);
            alert("Login failed");
        }
    };

    return (
        <Container maxWidth="sm">
            <Box display="flex" justifyContent="center" alignItems="center" minHeight="100vh" sx={{ bgcolor: '#cfe8fc'}} >
                <form onSubmit={handleSubmit}>
                    <Stack spacing={2}>
                        <TextField
                            id="outlined-basic"
                            label="Email"
                            variant="outlined"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="Email"
                            required/>
                        <TextField
                            id="outlined-basic"
                            label="Password"
                            type="password"
                            value={password}
                            variant="outlined"
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Email"
                            required/>
                        <Button type="submit" variant="contained" endIcon={<SendIcon/>}>
                            Login
                        </Button>
                    </Stack>
                </form>
            </Box>
        </Container>
    );
};

export default Login;
