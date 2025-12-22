import React, {useEffect, useState} from "react";
import api from "../security/axiosConfig";
import {Box, Card, CardContent, Container, Grid, Typography} from "@mui/material";

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
        <Grid container spacing={2}>
            <Container sx={{mt: 4}}>
                <Typography variant="h4" gutterBottom>
                    My Habits
                </Typography>
                {habits.map((habit: Habit) => (
                    <Box sx={{
                        p: 2,
                        gap: 2,
                        borderRadius: 2,
                        display: 'grid',
                        gridTemplateColumns: {md: '1fr 1fr'},
                    }}>
                        <Card elevation={3}>
                            <CardContent>
                                <Typography variant={"h6"} sx={{ mb: 1 }}>
                                    {habit.name}
                                </Typography>
                                <Typography variant={"subtitle1"}>
                                    Completed: {habit.completedDays.length}/{habit.targetPerWeek}
                                </Typography>
                                <Typography variant={"subtitle1"}>
                                    Streak: {habit.streak}
                                </Typography>
                            </CardContent>
                        </Card>
                    </Box>
                ))}
            </Container>
        </Grid>
    );
};

export default Dashboard;
