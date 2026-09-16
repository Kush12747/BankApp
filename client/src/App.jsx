import Users from "../src/Pages/Users";
import Navbar from "../src/Components/Navbar";
import Home from "../src/Pages/Home";
import Accounts from "../src/Pages/Accounts";
import { Routes, Route } from "react-router-dom";

function App() {
    return (
        <>
            <Navbar />

            <main>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/users" element={<Users />} />
                    <Route path="/accounts" element={<Accounts />} />
                </Routes>
            </main>
        </>
        
    );
}

export default App;