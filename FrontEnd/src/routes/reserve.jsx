import Header from './../middlewares/header';
import ReserveFlight from '../middlewares/reserveFlight';

const Reserve = () => {
    return (
        <>
            <Header />
            <ReserveFlight />
        </>
    );
};

export default Reserve;