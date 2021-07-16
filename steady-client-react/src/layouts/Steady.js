import {AppBar, makeStyles, Toolbar, Typography} from "@material-ui/core";
import steadyStyle from "../assets/jss/steadyStyle";
import SteadyList from "../components/SteadyList";
import moment from "moment";


const useStyles = makeStyles(steadyStyle);


export default function Steady() {

    const classes = useStyles();
    const m = moment();

    return (
        <div className={classes.wrapper}>
            <AppBar position="static">
                <Toolbar>
                    <Typography>STEADY {m.format('YYYY-MM-DD')}</Typography>
                </Toolbar>
            </AppBar>
            <SteadyList classes={classes}/>
        </div>
    );
}
