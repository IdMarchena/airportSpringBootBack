import Header from './../middlewares/header';
import FlightBookingHome from '../middlewares/flightBookingHome';

const Home = () => {
    return (
        <>
            <Header />
            <FlightBookingHome />
        </>
    );
};

export default Home;