import AdminNavbar from "../component/AdminNavbar";
import AdminHeader from "../component/AdminHeader";
import AccountUser from "../component/AccountUser";
import { useContext, useState } from "react";
import { MyUserContext } from "../App";
import ChartForMonth from "../component/ChartForMonth";
import { authApi, endpoints } from "../configs/APIS";
import Loading from "../component/Loading";
import { toast } from "react-toastify";
import ReportOfPost from "../component/ReportOfPost";

const Admin = () => {
  const [loading,setLoading]=useState(false);
  const [dataChart,setDataChart]=useState([]);
  const [user, dispatch] = useContext(MyUserContext);
  const [indexActive, setIndexActive] = useState(0);
  const handleSearch=(year)=>{
    const process=async()=>{
      try{
        setLoading(true);
        let{data}=await authApi().get(endpoints[`chart`],{params:{
          year:year,
        },});
        setDataChart(data);
        setLoading(false);
      }catch(error){
        console.info(error);
        setLoading(false);
      }
    }
    if(!isNaN(year)){
      process();
    }else{
      setDataChart([]);
      toast.error("The input value must be year");
    }
  }
  const renderComponent = () => {
    switch (indexActive) {
      case 0:
        return <AccountUser />;
      case 1:
        return <ReportOfPost/>;
    //   case 2:
    //     return <RepostForAuctionComponent />;
      case 3:
        return <ChartForMonth dataChart={dataChart}/>;
      default:
        return null;
    }
  };
  return (
    <>
    <main className="relative h-screen overflow-hidden bg-gray-100 dark:bg-gray-800 rounded-2xl">
      <div className="flex items-start justify-between">
        <div className="relative hidden h-screen my-4 ml-4 shadow-lg lg:block w-80">
          <div className="h-full bg-gray-200 dark:bg-gray-800 rounded-2xl ">
            <AdminNavbar setDataChart={setDataChart}indexActive={indexActive}setIndexActive={setIndexActive}/>
          </div>
        </div>
        <div class="flex flex-col w-full pl-0 md:p-4 md:space-y-4">
          <AdminHeader handleSearch={handleSearch} user={user}/>
        <div class="h-screen pt-2 text-black pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
          {renderComponent()}
        </div>
        </div>
      </div>
    </main>
    {loading&&<Loading/>}
    </>
    
  );
};

export default Admin;
