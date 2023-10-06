import { Link } from "react-router-dom";
import AdminNavbar from "../component/AdminNavbar";
import AdminHeader from "../component/AdminHeader";

const Admin = () => {
  return (
    <main className="relative h-screen overflow-hidden bg-gray-100 dark:bg-gray-800 rounded-2xl">
      <div className="flex items-start justify-between">
        <div className="relative hidden h-screen my-4 ml-4 shadow-lg lg:block w-80">
          <div className="h-full bg-gray-200 dark:bg-gray-800 rounded-2xl ">
            <AdminNavbar />
          </div>
        </div>
        <div class="flex flex-col w-full pl-0 md:p-4 md:space-y-4">
          <AdminHeader />
          <div class="h-screen pt-2 pb-24 pl-2 pr-2 overflow-auto md:pt-0 md:pr-0 md:pl-0">
        </div>
        </div>
      </div>
      
    </main>
  );
};

export default Admin;
