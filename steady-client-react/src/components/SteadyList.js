import {
    Checkbox,
    IconButton,
    List,
    ListItem,
    ListItemIcon,
    ListItemSecondaryAction,
    ListItemText
} from "@material-ui/core";
import CommentIcon from "@material-ui/icons/Comment";
import {useEffect, useState} from "react";
import Api from "../utils/Api";



export default function SteadyList(classes) {

    const [checkList, setCheckList] = useState({
        data : [
            {
                "id" : 1,
                "name" : "TODO1",
                "startTime" : "14:00",
                "endTime" : "15:00",
                "checked" : false,
            },
            {
                "id" : 2,
                "name" : "TODO2",
                "startTime" : "16:00",
                "endTime" : "10:00",
                "checked" : false,
            }
        ]
    });

    useEffect(() => {
        Api.getList()
            .then((response) => {
                console.log(response);
                setCheckList(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);


    const handleToggle = (value) => () => {
        setCheckList(
            {
                data : checkList.data.map(data => {
                        if (data.steadyId === value) {
                            Api.finished(value);
                            return {...data, finished: !data.finished}
                        } else {
                            return data;
                        }
                    }
                )
            }
        )
    };

    return (
        <List className={classes.root}>
            {checkList.data.map((data) => {
                const labelId = `checkbox-list-label-${data.steadyId}`;

                return (
                    <ListItem key={data.steadyId} role={undefined} dense button onClick={handleToggle(data.steadyId)}>
                        <ListItemIcon>
                            <Checkbox
                                edge="start"
                                checked={data.finished}
                                tabIndex={-1}
                                disableRipple
                                inputProps={{ 'aria-labelledby': labelId }}
                            />
                        </ListItemIcon>
                        <ListItemText id={labelId} primary={`${data.steadyName}`} secondary={`${data.startTime}~${data.endTime}`}/>
                        <ListItemSecondaryAction>
                            <IconButton edge="end" aria-label="comments">
                                <CommentIcon />
                            </IconButton>
                        </ListItemSecondaryAction>
                    </ListItem>
                );
            })}
        </List>
    )
}
