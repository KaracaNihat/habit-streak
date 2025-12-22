import React, {useEffect, useState} from "react";
import api from "../security/axiosConfig";
import {
    Box,
    Button,
    Card, CardActions,
    CardContent,
    Chip,
    Container,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    Grid,
    TextField,
    Typography
} from "@mui/material";
import {Delete} from "@mui/icons-material";

interface Habit {
    id: string;
    name: string;
    targetPerWeek: number;
    completedDays: string[];
    createdAt: string;
    streak: number;
}

const Dashboard: React.FC = () => {
    const [habits, setHabits] = useState<Habit[]>([]);
    const [openDialog, setOpenDialog] = useState<boolean>(false);
    const [habitName, setHabitName] = useState("");
    const [habitTarget, setHabitTarget] = useState(1);
    const [completedDays, setCompletedDays] = useState<string[]>([]);
    const isCreateDisabled = habitName.trim() === "" || habitTarget <= 0;


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

    const handleOpenCreate = () => {
        setOpenDialog(true);
        setHabitName("");
        setHabitTarget(1);
        setCompletedDays([]);
    }

    const handleCreateHabit = async () => {
        const payload = {
            name: habitName,
            targetPerWeek: habitTarget,
            completedDays: completedDays,
        }

        const res = await api.post("/api/habits", payload);
        setHabits([...habits, res.data]);
        setOpenDialog(false);
    }

    const handleDelete = async (id: string) => {
        await api.delete(`/api/habits/${id}`);
        setHabits(habits.filter(h => h.id !== id));
    }

    return (
        <Box sx={{minHeight: "100vh", backgroundColor: "#63a3e3"}}>
            <Grid container spacing={2}>
                <Container sx={{mt: 3}}>
                    <Box textAlign="center">
                        <Typography variant="h3" sx={{mb: 2}}>
                            My Habits
                        </Typography>
                        <Button variant="contained" sx={{mt: 2, mb: 3, bgcolor: "#14e316"}} onClick={handleOpenCreate}>
                            Add Habit ➕
                        </Button>
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
                            <Card elevation={3} sx={{width: '100%', maxWidth: 700}}>
                                <CardContent>
                                    <Typography variant="h6" sx={{mb: 1}}>
                                        {habit.name}
                                    </Typography>
                                    <Typography variant="subtitle1">
                                        Completed: {habit.completedDays.length}/{habit.targetPerWeek}
                                    </Typography>
                                    <Chip
                                        label={habit.streak ? `${habit.streak} 🔥` : "0 🔥"}
                                        sx={{mt: 1}}
                                        variant="outlined"
                                    />
                                </CardContent>
                                <CardActions sx={{ justifyContent: "flex-end" }}>
                                    <Button size="small" onClick={() => handleDelete(habit.id)}>
                                        <Delete color="error"/>
                                    </Button>
                                </CardActions>
                            </Card>
                        </Box>
                    ))}
                </Container>
            </Grid>
            <Dialog open={openDialog} onClose={() => setOpenDialog(false)}>
                <DialogTitle>Add Habit</DialogTitle>
                <DialogContent>
                    <TextField
                        label="Name"
                        fullWidth
                        sx={{mt: 1}}
                        value={habitName}
                        onChange={e => setHabitName(e.target.value)}
                        required
                    />
                    <TextField
                        label="Target Per Week"
                        type="number"
                        fullWidth
                        sx={{mt: 1}}
                        value={habitTarget}
                        onChange={e => setHabitTarget(Number(e.target.value))}
                        required
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCreateHabit} disabled={isCreateDisabled}>Create</Button>
                </DialogActions>
            </Dialog>
        </Box>
    );
};

export default Dashboard;
