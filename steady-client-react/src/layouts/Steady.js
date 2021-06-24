import {AppBar, makeStyles, Toolbar, Typography} from "@material-ui/core";
import steadyStyle from "../assets/jss/steadyStyle";
import SteadyList from "../components/SteadyList";


const useStyles = makeStyles(steadyStyle);


export default function Steady() {

    const classes = useStyles();

    return (
        <div className={classes.wrapper}>
            <AppBar position="static">
                <Toolbar>
                    <Typography>STEADY</Typography>
                </Toolbar>
            </AppBar>
            <SteadyList classes={classes}/>
        </div>
    );
}
