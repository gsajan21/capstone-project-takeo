import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom'; // Import BrowserRouter
import './index.css';
import RegisterPage from './pages/RegisterPage'; // Import RegisterPage without .tsx extension

ReactDOM.render(
  <Router>
    <RegisterPage />
  </Router>,
  document.getElementById("root")
);
