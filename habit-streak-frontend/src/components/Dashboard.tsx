import React, {useEffect, useState} from "react";
import api from "../security/axiosConfig";
import {Box, Card, CardContent, Chip, Container, Grid, Typography} from "@mui/material";

interface Habit {
    id: string;
    name: string;
    targetPerWeek: number;
    completedDays: Date[];
    createdAt: string;
    streak: number;
}

const Dashboard: React.FC = () => {
    const [habits, setHabits] = useState<Habit[]>([]);

    useEffect(() => {
        const getHabits = async () => {
            try {
                const res = await api.get<Habit[]>("/api/habits");
                setHabits(res.data);
            } catch (err) {
                console.log(err);
            }
        }
        getHabits();
    }, []);

    return (
        <Box sx={{minHeight: "100vh", backgroundColor: "#63a3e3"}}>
            <Grid container spacing={2}>
                <Container sx={{mt: 3}}>
                    <Box textAlign="center">
                        <Typography variant="h3" sx={{mb: 2}}>
                            My Habits
                        </Typography>
                    </Box>
                    {habits.map((habit: Habit) => (
                        <Box
                            key={habit.id}
                            sx={{
                                display: 'flex',
                                justifyContent: 'center',
                                mb: 2,
                            }}
                        >
                            <Card elevation={3} sx={{width: '100%', maxWidth: 500}}>
                                <CardContent>
                                    <Typography variant="h6" sx={{mb: 1}}>
                                        {habit.name}
                                    </Typography>
                                    <Typography variant="subtitle1">
                                        Completed: {habit.completedDays.length}/{habit.targetPerWeek}
                                    </Typography>
                                    <Chip
                                        label={`${habit.streak} 🔥`}
                                        sx={{mt: 1}}
                                        variant="outlined"
                                    />
                                </CardContent>
                            </Card>
                        </Box>
                    ))}
                </Container>
            </Grid>
        </Box>
    );
};

export default Dashboard;
