import React from 'react';
import { Switch, Route } from 'react-router-dom';
import Home from './pages/Home/Home'
import Login from './pages/Login/Login';
import TakeQuiz from './pages/TakeQuiz/TakeQuiz'
import QuizBuilder from './components/professor/QuizBuilder';

/**+
 *
 * @returns {*}
 * @constructor: The Main Router Class
 */
const Routes = () => (
    <main>
    <Switch>
        <Route exact path='/' component={Login}></Route>
        <Route exact path='/home' component={Home}></Route>
        <Route exact path='/login' component={Login}></Route>
        <Route exact path='/takeQuiz' component={TakeQuiz}></Route>
        <Route exact path='/createQuiz' component={QuizBuilder}></Route>
    </Switch>
    </main>
);

export default Routes;