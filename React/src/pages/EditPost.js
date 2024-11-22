import React, {useEffect, useState, useContext} from "react";
import { BoardContext } from "../context/BoardContext";
import CustomButton from "../components/CustomButton";
import CustomInput from "../components/CusomInput";
import { useNavigate,useParams } from "react-router-dom";
import axios from "axios";

const EditPost = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const [post, setPost] = useState({})
    const {boardList,setBoardList} = useContext(BoardContext);

    const {author, title, content} = post;

    useEffect(() => {
       //수정한 내용을 데이터베이스에 저장
       const getBoardData = async () => {
        try {
          const response = await axios.get(`http://localhost:9090/api/board/get/${id}`);
          setPost(response.data.data[0]); // 데이터 상태를 업데이트
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      };
      
      getBoardData();
    },[])

    const backToPost = () => {
        navigate("/post/"+id);
    }

    const updatePost = async() => {
        const response = await axios(`http://localhost:9090/api/board/modify/${id}`,{
            headers:{
                "Content-Type":"application/json"
            },
            data: JSON.stringify(post),
            method:'put',
        })
        console.log(response);

        if(response.data){
            alert('수정이 완료되었습니다.');
            navigate("/post/"+id);
        } else {
            alert('수정에 실패하였습니다.');
        }
        
    }

    return(
        <div>
            <h1>글 수정하기</h1>
            <CustomInput label="제목" value={title} onChange={(
                e => setPost((prevPost) => ({
                ...prevPost,
                title : e.target.value,
            })))}
            />
            <CustomInput label="작성자" value={author} onChange={(
                e => setPost((prevPost) => ({
                ...prevPost,
                author : e.target.value,
            })))}/>
            <CustomInput 
                label="내용" 
                multiline
                rows={6}
                value={content} 
                onChange={(
                    e => setPost((prevPost) => ({
                    ...prevPost,
                    content : e.target.value,
                })))}/>
            <div>
                <CustomButton label="수정 완료" onClick={updatePost} />
                <CustomButton 
                    label="수정 취소" 
                    variant="outlined" 
                    color="secondary"
                    onClick={backToPost}
                />
            </div>
        </div>
    )
}

export default EditPost;