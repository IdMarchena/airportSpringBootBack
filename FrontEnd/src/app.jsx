import { createBrowserRouter, RouterProvider } from "react-router-dom";
import './../src/app.scss';
import Auth from "../src/routes/auth";
import ButtonTheme from "./middlewares/buttonTheme";
import Home from "../src/routes/home";
import FlightResults from "../src/routes/flightResults";
import Reserve from '../src/routes/reserve';

const router = createBrowserRouter(
  [
    { path: "/", element: <><Home /></> },
    { path: "/home", element: <><Home /></> },
    { path: "/login", element: <><Auth route="login" /></> },
    { path: "/signup", element: <><Auth route="signup" /></> },
    { path: "/flight-results", element: <><FlightResults /></> },
    { path: "/reserves", element: <><Reserve /></> }
  ],
  {
    future: {
      v7_startTransition: true,
    },
  }
);

function App() {
  return (
    <>
      <RouterProvider router={router} />
      <ButtonTheme />
    </>
  );
}

export default App;