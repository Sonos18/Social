
const AdminNavbar = ({indexActive,setIndexActive,setDataChart}) => {
  const handleActive = (i) => {
    setDataChart([]);
    setIndexActive(i);
  };
  
  const component = [
    { text: "Users", com: true },
    { text: "ReportForPost", com: true },
    { text: "RepostForAuction", com: true },
    { text: "Chart", com: true },
  ];
  return (
    <div className="bg-gray-200 dark:bg-gray-800">
      <div className="container mx-auto">
        <div className="flex items-center justify-center pt-6">LOGO</div>
        <div>
          <nav className="flex items-center justify-between flex-wrap p-6 mt-8">
            {component.map((c, index) => (
              <div
                key={index}
                className={`${
                  index === indexActive
                    ? "flex items-center justify-start w-full p-4 my-4 font-thin text-blue-500 uppercase transition-colors duration-200 border-r-4 border-blue-500 bg-gradient-to-r from-white to-blue-100 dark:from-gray-700 dark:to-gray-800"
                    : "flex items-center justify-start w-full p-4 my-4 font-thin text-gray-500 uppercase transition-colors duration-200 dark:text-gray-200 hover:text-blue-500"
                }`}
                onClick={() => handleActive(index)}
              >
                <span className="mx-4 text-sm font-normal" />
                <span className="mx-4 text-sm font-normal">{c.text}</span>
              </div>
            ))}
          </nav>
        </div>
      </div>
    </div>
  );
};

export default AdminNavbar;
