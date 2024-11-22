import React,{useState, useContext} from "react"
import { BoardContext } from "../context/BoardContext";
import { useNavigate } from "react-router-dom";
import CustomInput from "../components/CusomInput";
import CustomButton from "../components/CustomButton";
import axios from "axios";

const WritePost = () => {

    const navigate = useNavigate();

    const [title,setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [content, setContent] = useState('');

    const {boardList, setBoardList} = useContext(BoardContext)

    const savePost= (e) => {
        e.preventDefault();

    //id는 자동으로 생성
    //등록날짜는 서버측에서 
    const newPost = {
            title,
            author,
            content,
        }
    
    // const response = axios.post('http://localhost:9090/api/board/write',newPost,{
    //     headers:{
    //         "Content-Type":"application/json"
    //     },
    // })

    const response = axios('http://localhost:9090/api/board/write',{
        headers:{
            "Content-Type":"application/json"
        },
        data: JSON.stringify(newPost),
        method:'post',
    })
    console.log(response.data);
    //작성 후 메인화면으로 이동하기
    alert("게시물이 등록되었습니다.");
    navigate("/");
    }

    const backToBoard = () => {
        navigate("/");
    }
    return(
        <div>
            <h1>글쓰기</h1>
            <form>
                <CustomInput label="제목" value={title} onChange={(e)=>{setTitle(e.target.value)}}/>
                <CustomInput label="작성자" value={author} onChange={(e)=>{setAuthor(e.target.value)}}/>
                <CustomInput
                    label="내용"
                    multiline
                    rows={6}
                    value={content}
                    onChange={(e)=>{setContent(e.target.value)}}
                />
                <div>
                    <CustomButton label="저장" onClick={savePost}/>
                    <CustomButton label="취소" variant="outlined" color="secondary" onClick={backToBoard}/>
                </div>
            </form>
        </div>
    );
}

export default WritePost;