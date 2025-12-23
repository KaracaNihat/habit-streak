import React, {useState} from "react";
import {registerUser} from "../security/authApi";
import {Box, Button, Container, Stack, TextField, Typography} from "@mui/material";
import AddRoundedIcon from '@mui/icons-material/AddRounded';
import {useNavigate} from "react-router-dom";

const Register: React.FC = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const res = await registerUser({email, password});
            if (res.status === 200) {
                alert("Account Created Successfully!");
                navigate("/");
            } else {
                alert("Registering failed: " + res.status);
            }
        } catch (err) {
            console.error(err);
            alert("Registering failed");
        }
    };

    return (
        <Box
            sx={{
                minHeight: "100vh",
                backgroundColor: "#46a6e4",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
            }}
        >
            <Typography
                variant="h1"
                component="h1"
                sx={{
                    color: "#ffffff",
                    fontWeight: "bold",
                    mb: 6,
                    textAlign: "center",
                }}
            >
                🔥Habit Streak🔥
            </Typography>
            <Container maxWidth="sm"
                       sx={{
                           backgroundColor: "#ffffff",
                           p: 4,
                           borderRadius: 2,
                           boxShadow: 3,
                       }}>
                <form onSubmit={handleSubmit}>
                    <Stack spacing={2}>
                        <Typography variant="h4" component="h1" textAlign="center">
                            Sign Up
                        </Typography>
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
                        <Button type="submit" variant="contained" endIcon={<AddRoundedIcon/>}>
                            Create Account
                        </Button>
                        <Typography textAlign="center">
                            Already have an account? <a href="/">Sign in here</a>
                        </Typography>
                    </Stack>
                </form>
            </Container>
        </Box>
    );
};

export default Register;
