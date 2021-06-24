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
import {useState} from "react";



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

    const handleToggle = (value) => () => {
        setCheckList(
            {
                data : checkList.data.map(data =>
                    (data.id === value) ? {...data, checked : !data.checked} : data
                )
            }
        )
    };

    return (
        <List className={classes.root}>
            {checkList.data.map((data) => {
                const labelId = `checkbox-list-label-${data.id}`;

                return (
                    <ListItem key={data.id} role={undefined} dense button onClick={handleToggle(data.id)}>
                        <ListItemIcon>
                            <Checkbox
                                edge="start"
                                checked={data.checked}
                                tabIndex={-1}
                                disableRipple
                                inputProps={{ 'aria-labelledby': labelId }}
                            />
                        </ListItemIcon>
                        <ListItemText id={labelId} primary={`Line item ${data.name}`} secondary={`${data.startTime}~${data.endTime}`}/>
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
